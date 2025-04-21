document.getElementById('eventoForm').addEventListener('submit', function (e) {
    e.preventDefault();

    const nomeEvento = document.getElementById('nomeEvento').value;
    const descricaoEvento = document.getElementById('descricaoEvento').value;
    const dataEvento = document.getElementById('dataEvento').value;

    fetch('/evento', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ nomeEvento, descricaoEvento, dataEvento })
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(text => { throw new Error(text) });
        }
        return response.json();
    })
    .then(data => {
        document.getElementById('resposta').innerHTML = `
            <div class="card-panel green lighten-4 green-text text-darken-4">
                Evento criado com sucesso!
            </div>`;
    })
    .catch(error => {
        document.getElementById('resposta').innerHTML = `
            <div class="card-panel red lighten-4 red-text text-darken-4">
                Erro: ${error.message}
            </div>`;
    });
});