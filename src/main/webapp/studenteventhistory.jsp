<%@page import="java.util.List"%>
<%@page import="com.klef.ep.models.Event"%>
<%@page import="com.klef.ep.services.StudentService"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="com.klef.ep.models.Student"%>
<%@include file="studentnav.jsp" %>
<%
Student student = (Student)session.getAttribute("student");
InitialContext context = new InitialContext();
StudentService service = (StudentService)context.lookup("java:global/EPProject/StudentServiceImpl!com.klef.ep.services.StudentService");
String sid = student.getId();
List<Event> eventList = service.vieweventhistory(sid);
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

      .view-link {
        color: red;
        text-decoration: none;
        transition: color 0.3s;
      }

      .view-link:hover {
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
			<th>Event Faculty ID</th>
			<th>Event Faculty Name</th>
			<th>Event Venue</th>
			<th>Event Date</th>
			<th>ACTIONS</th>
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
			<td><%=e.getFacultyid() %></td>
			<td><%=e.getFacultyname() %></td>
			<td><%=e.getEvenue() %></td>
			<td><%=e.getEdate() %></td>
			<td>
				<a href="studentvieweventbyid.jsp?eid=<%=e.getEid() %>" class="view-link">View</a> 
			 </td>
			</tr>
			<%		
		}
	%>
		
	</tbody>
</table>