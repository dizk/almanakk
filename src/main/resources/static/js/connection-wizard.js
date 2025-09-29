// Enable/disable Start Exploring button based on connection status
document.body.addEventListener('htmx:afterSwap', function(event) {
    if (event.detail.target.id === 'connectionStatus') {
        const successAlert = event.detail.target.querySelector('.alert-success');
        const startExploringBtn = document.getElementById('startExploringBtn');
        startExploringBtn.disabled = !successAlert;
    }
});

// Handle Start Exploring button
document.getElementById('connectionForm').addEventListener('submit', function(event) {
    event.preventDefault();
    const jdbcUrl = document.getElementById('jdbcUrl').value;
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const form = new FormData();
    form.append('jdbcUrl', jdbcUrl);
    form.append('username', username);
    form.append('password', password);

    fetch('/start-exploring', {
        method: 'POST',
        body: form
    }).then(response => {
        if (response.redirected) {
            window.location.href = response.url;
        }
    });
});