<%@page import="com.klef.ep.models.Event"%>
<%@include file="adminnav.jsp" %>
<%@page import="com.klef.ep.models.RegisteredEvents"%>
<%@page import="java.util.List"%>
<%@page import="com.klef.ep.services.AdminService"%>
<%@page import="javax.naming.InitialContext"%>
<%
String eid = request.getParameter("id");
InitialContext context = new InitialContext();
AdminService service = (AdminService)context.lookup("java:global/EPProject/AdminServiceImpl!com.klef.ep.services.AdminService");
List<RegisteredEvents> eventslist = service.viewRegisteredStudents(eid);
Event event = service.vieweventbyID(eid);
Long registeredCount = service.getregisteredCount(eid);
String winner = service.getEventWinner(eid);
%>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #eef2f7;
    margin: 0;
    padding: 0;
}
.container {
    width: 90%;
    margin: auto;
    overflow: hidden;
}
.styled-table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    background-color: white;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
    overflow: hidden;
}

.styled-table th,
.styled-table td {
    padding: 15px 20px;
    text-align: center;
    border-bottom: 1px solid #e1e1e1;
}

.styled-table thead {
    background-color: #007bff;
    color: #fff;
    font-weight: bold;
}

.styled-table tbody tr {
    transition: background-color 0.3s, transform 0.3s;
}

.styled-table tbody tr:hover {
    background-color: #f2f2f2;
    transform: scale(1.02);
}

.styled-table tbody tr:nth-of-type(even) {
    background-color: #f9f9f9;
}

.search-bar {
    margin: 20px 0;
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

.event-info {
    display: flex;
    justify-content: center;
    gap: 40px;
    margin: 20px 0;
    flex-wrap: wrap;
    font-size: 1.1em;
    background-color: rgba(255, 255, 255, 0.8); /* semi-transparent white background */
    backdrop-filter: blur(10px); /* blur effect */
    border-radius: 10px; /* optional: rounded corners */
    padding: 20px;
}

.event-info p {
    margin: 0;
}

a.submit-attendance {
    display: inline-block;
    margin-top: 20px;
    padding: 10px 20px;
    background-color: #007bff;
    color: white;
    text-decoration: none;
    border-radius: 5px;
    transition: background-color 0.3s, transform 0.3s;
}

a.submit-attendance:hover {
    background-color: #0056b3;
    transform: scale(1.05);
}
</style>
<script type="text/javascript" src="resources/js/tablesearch.js"></script>
<div class="container">
    <h3 align="center"><u>View Registered Students</u></h3>
    <div align="center" class="search-bar">
        <input type="text" id="searchInput" onkeyup="filterTable()" placeholder="Search for students.."/>
    </div>
    <div class="event-info">
        <p>Event ID: <%= event.getEid() %></p>
        <p>Event Name: <%= event.getEname() %></p>
        <p>Faculty ID: <%= event.getFacultyid() %></p>
        <p>Faculty Name: <%= event.getFacultyname() %></p>
        <p>Event Winner : <%= winner%></p>
        <p>Registered Count : <%= registeredCount %></p>
    </div>
    <div align="center">
        <a href="adminstudentattendence.jsp?id=<%= eid %>" class="submit-attendance">Submit Attendance</a>
        <a href="adminpdatewinner.jsp?id=<%= eid %>" class="submit-attendance">SUBMIT WINNER</a>
        <a href="downloadregisteredstudents?eid=<%= eid %>" class="submit-attendance">DOWNLOAD REGISTERED STUDENTS</a>
    </div> 
    <table class="styled-table" id="dataTable" border="2">
        <thead>
            <tr>
                <th>Student ID</th>
                <th>Student Name</th>
                <th>Status</th>
                <th>Win Status</th>
            </tr>
        </thead>
        <tbody>
            <%
            for (RegisteredEvents e : eventslist) {
                String statusColor;
                if ("ATTENDED".equals(e.getStatus())) {
                    statusColor = "green";
                } else if ("NOT_ATTENDED".equals(e.getStatus())) {
                    statusColor = "red";
                } else if ("CANCELLED".equals(e.getStatus())) {
                    statusColor = "red";
                } else {
                    statusColor = "black"; // Default color for any other status
                }
            %>
                <tr>
                    <td><%= e.getStudentid() %></td>
                    <td><%= e.getStudentname() %></td>
                    <td style="color:<%= statusColor %>;"><%= e.getStatus() %></td>
                    <td><%= e.getWinstatus() %></td>
                </tr>
            <%
            }
            %>
        </tbody>
    </table>
</div>
