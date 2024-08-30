function filterTable() {
  var input, filter, table, tr, td, i, j, txtValue, noMatch;
  input = document.getElementById("searchInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("dataTable");
  tr = table.getElementsByTagName("tr");
  noMatch = true; // Assume no match initially

  // Remove any existing "No Records Found" rows
  removeNoMatchRows();

  for (i = 1; i < tr.length; i++) {
    tr[i].style.display = "none";
    td = tr[i].getElementsByTagName("td");
    for (j = 0; j < td.length; j++) {
      if (td[j]) {
        txtValue = td[j].textContent || td[j].innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
          tr[i].style.display = "";
          noMatch = false; // Found a match
          break;
        }
      }
    }
  }

  // Show "No Records Found" message if no match
  if (noMatch) {
    var noMatchRow = table.insertRow(-1); // Add a new row at the end of the table
    var noMatchCell = noMatchRow.insertCell(0);
    noMatchCell.colSpan = tr[0].cells.length; // Set colspan to the number of columns in the header row
    noMatchCell.textContent = "No Records Found.";
    noMatchCell.style.textAlign = "center";
    noMatchCell.style.fontWeight = "bold";
    noMatchCell.style.backgroundColor = "#f4f4f4"; // Optional: to match the table's background color
  }
}

function removeNoMatchRows() {
  var table = document.getElementById("dataTable");
  var tr = table.getElementsByTagName("tr");
  for (var i = tr.length - 1; i >= 0; i--) {
    if (tr[i].cells.length === 1 && tr[i].cells[0].textContent === "No Records Found.") {
      table.deleteRow(i);
    }
  }
}
