<script>
document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('eventoForm');
    const mensagemDiv = document.getElementById('mensagem');

    carregarEventos();

    form.addEventListener('submit', function (e) {
        e.preventDefault();

        const nomeEvento = document.getElementById('nomeEvento').value;
        const descricaoEvento = document.getElementById('descricaoEvento').value;
        const dataEvento = document.getElementById('dataEvento').value;

        const dto = { nomeEvento, descricaoEvento, dataEvento };

        fetch('http://localhost:8080/evento', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dto)
        })
        .then(async response => {
            let data = {};
            try {
                data = await response.json();
            } catch (e) {
                // Não há corpo JSON (ex: erro 204 ou corpo vazio)
            }

            if (!response.ok) {
                // Pega a mensagem direta ou concatena mensagens dos erros detalhados
                let msg = data?.message || (data?.errors ? Object.values(data.errors).join('<br>') : '') || 'Erro ao criar evento.';
                throw new Error(msg);
            }
            return data;
        })
        .then(data => {
            exibirMensagem('Evento criado com sucesso!', true);
            form.reset();
            carregarEventos();
        })
        .catch(error => {
            exibirMensagem(`Erro: ${error.message}`, false);
            console.error('Erro capturado:', error);
        });
    });

    function carregarEventos() {
        fetch('http://localhost:8080/evento')
            .then(res => res.json())
            .then(data => {
                const lista = document.getElementById('listaEventos');
                lista.innerHTML = '';

                if (data.length === 0) {
                    lista.innerHTML = '<li class="collection-item">Nenhum evento cadastrado.</li>';
                    return;
                }

                data.forEach(evento => {
                    const item = document.createElement('li');
                    item.className = 'collection-item';
                    item.innerHTML = `
                        <div style="overflow: hidden;">
                            <div>
                                <strong>${evento.nomeEvento}</strong><br>
                                ${evento.descricaoEvento}<br>
                                <em>${evento.dataEvento}</em>
                            </div>
                            <button class="btn red btn-small deletar-evento right" data-id="${evento.id}" style="margin-top: -1px;">Excluir</button>
                        </div>
                    `;
                    lista.appendChild(item);
                });

                document.querySelectorAll('.deletar-evento').forEach(botao => {
                    botao.addEventListener('click', function () {
                        const id = this.getAttribute('data-id');
                        if (confirm('Tem certeza que deseja excluir este evento?')) {
                            fetch(`http://localhost:8080/evento/${id}`, {
                                method: 'DELETE'
                            })
                            .then(response => {
                                if (!response.ok) {
                                    throw new Error('Erro ao excluir o evento');
                                }
                                exibirMensagem('Evento excluído com sucesso!', true);
                                carregarEventos();
                            })
                            .catch(error => {
                                exibirMensagem(`Erro ao excluir: ${error.message}`, false);
                                console.error(error);
                            });
                        }
                    });
                });
            })
            .catch(err => {
                console.error('Erro ao carregar eventos:', err);
                exibirMensagem('Erro ao carregar eventos.', false);
            });
    }

    function exibirMensagem(texto, sucesso = true) {
        const cor = sucesso ? 'green' : 'red';
        mensagemDiv.innerHTML = '';

        const div = document.createElement('div');
        div.className = `card-panel ${cor} lighten-4`;
        div.innerHTML = `<span class="${cor}-text text-darken-4">${texto}</span>`;
        mensagemDiv.appendChild(div);

        setTimeout(() => {
            div.style.opacity = '1';
            let op = 1;
            const fadeOut = setInterval(() => {
                if (op <= 0.1) {
                    clearInterval(fadeOut);
                    div.remove();
                }
                div.style.opacity = op;
                div.style.transition = 'opacity 0.3s';
                op -= 0.1;
            }, 30);
        }, 4000);
    }
});
</script>
