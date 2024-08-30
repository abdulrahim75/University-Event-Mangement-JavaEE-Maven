function filterCards() {
    let input = document.getElementById('searchInput');
    let filter = input.value.toLowerCase();
    let container = document.getElementById('eventContainer');
    let cards = container.getElementsByClassName('card');

    for (let i = 0; i < cards.length; i++) {
        let card = cards[i];
        let title = card.querySelector('.card-header').textContent.toLowerCase();
        let body = card.querySelector('.card-body').textContent.toLowerCase();

        if (title.includes(filter) || body.includes(filter)) {
            card.style.display = '';
        } else {
            card.style.display = 'none';
        }
    }
}
