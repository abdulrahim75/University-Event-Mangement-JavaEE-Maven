<%@page import="com.klef.ep.models.Faculty"%>
<%@page import="com.klef.ep.models.Event"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="com.klef.ep.services.FacultyService"%>
<%@include file="facultynavbar.jsp" %>
<%
Faculty faculty = (Faculty)session.getAttribute("faculty");
InitialContext context = new InitialContext();
FacultyService service = (FacultyService)context.lookup("java:global/EPProject/FacultyServiceImpl!com.klef.ep.services.FacultyService");
List<Event> eventList = service.viewEventHistory(faculty.getId());
%>
<style>
body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .styled-table {
        width: 80%;
        border-collapse: collapse;
        margin: 20px auto;
        background-color: white;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
      }

      .styled-table th,
      .styled-table td {
        padding: 12px 15px;
        text-align: center;
      }

      .styled-table thead {
        background-color: lightblue;
        color: #333;
      }

      .styled-table tbody tr {
        transition: background-color 0.3s;
      }

      .styled-table tbody tr:hover {
        background-color: #f2f2f2;
      }

      .delete-link {
        color: red;
        text-decoration: none;
        transition: color 0.3s;
      }

      .delete-link:hover {
        color: darkred;
      }
        .search-bar {
            margin: 20px;
            text-align: center;
        }
        .search-bar input[type="text"] {
            padding: 10px;
            width: 300px;
            font-size: 1em;
            border: 1px solid #007bff;
            border-radius: 5px;
            outline: none;
        }
        .search-bar input[type="text"]:focus {
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }
</style>
<script type="text/javascript" src="resources/js/tablesearch.js"></script>
<h3 align="center" ><u>Event History</u></h3>
<div align="center" class="search-bar">
    <input type="text" id="searchInput" onkeyup="filterTable()" placeholder="Search for events.."/>
</div>
<table class="styled-table" id="dataTable" border="2" >
	<thead>
		<tr>
			<th>Event ID</th>
			<th>Event Name</th>
			<th>Event Domain</th>
			<th>Event Department</th>
			<th>Event Venue</th>
			<th>Event Date</th>
			<th>Event Capacity</th>
			<th>Event Current Availability</th>
			<th>Actions</th>
		</tr>
	</thead>
	<tbody>
		
	<%
		for(Event e:eventList){
			%>
			<tr>
			<td><%=e.getEid() %></td>
			<td><%=e.getEname() %></td>
			<td><%=e.getedomain() %></td>
			<td><%=e.getEdepartment() %></td>
			<td><%=e.getEvenue() %></td>
			<td><%=e.getEdate() %></td>
			<td><%=e.getParticipants() %></td>
			<td><%=e.getAvailability() %></td>
			<td>
				<a href="facultyviewevent.jsp?eid=<%=e.getEid() %>" class="delete-link">View</a>
				<br><br>
				<a href="facultyviewregisteredstudents.jsp?eid=<%=e.getEid() %>" class="delete-link">View Registered Students</a>
			</td>
			</tr>
			<%		
		}
	%>
		
	</tbody>
</table>