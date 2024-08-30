$(document).ready(function() {
    $(".datepicker").datepicker({
        dateFormat: 'dd/mm/yy', // Change the format to dd/mm/yy
        changeMonth: true,
        changeYear: true,
        yearRange: "1945:2024"
    });
});