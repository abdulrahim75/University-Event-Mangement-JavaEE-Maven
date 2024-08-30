function checkWinner(checkbox) {
    var checkboxes = document.querySelectorAll('input[name="winnerStudent"]');
    checkboxes.forEach(function(cb) {
        if (cb !== checkbox) {
            cb.checked = false;
            cb.disabled = checkbox.checked;
        }
 });
}