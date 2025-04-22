document.addEventListener('DOMContentLoaded', function () {
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
            const data = await response.json();

            if (!response.ok) {
                // Tenta pegar mensagem do backend
                let msg = data?.message || Object.values(data).join('<br>') || 'Erro ao criar evento.';
                throw new Error(msg);
            }

            exibirMensagem('Evento criado com sucesso!', true);
            form.reset();
            carregarEventos();
        })
        .catch(error => {
            // Mostra mensagem detalhada vinda do backend ou erro de rede
            exibirMensagem(`Erro: ${error.message}`, false);
            console.error('Erro capturado:', error);
        });
    });

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
                    const item = `
                        <li class="collection-item">
                            <strong>${evento.nomeEvento}</strong><br>
                            ${evento.descricaoEvento}<br>
                            <em>${evento.dataEvento}</em>
                        </li>`;
                    lista.innerHTML += item;
                });
            })
            .catch(err => {
                console.error('Erro ao carregar eventos:', err);
                exibirMensagem('Erro ao carregar eventos.', false);
            });
    }
});
