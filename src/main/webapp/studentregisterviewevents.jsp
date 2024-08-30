<%@page import="java.text.ParseException"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.klef.ep.models.Event"%>
<%@page import="com.klef.ep.services.StudentService"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="com.klef.ep.models.Student"%>
<%@include file="studentnav.jsp" %>
<head>
<title>
Student Event Registration

</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<%
Student student = (Student)session.getAttribute("student");
InitialContext context = new InitialContext();
StudentService service = (StudentService)context.lookup("java:global/EPProject/StudentServiceImpl!com.klef.ep.services.StudentService");
List<Event> eventList = service.viewevents();
SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");

Date currentDate = new Date();

%>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
}
.container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;
    padding: 20px;
}
.card {
    background-color: white;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    transition: transform 0.3s;
    width: 300px;
    overflow: hidden;
}
.card:hover {
    transform: translateY(-10px);
}
.card-header {
    background-color: #007bff;
    color: white;
    padding: 10px;
    text-align: center;
    font-size: 1.25em;
}
.card-body {
    padding: 15px;
}
.card-body p {
    margin: 0 0 10px;
}
.card-footer {
    padding: 10px;
    text-align: center;
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

<h3 align="center"><u>Event History</u></h3>
<div align="center" class="search-bar">
    <input type="text" id="searchInput" onkeyup="filterCards()" placeholder="Search for events.." />
</div>
<div class="container" id="eventContainer">
    <%
    for(Event e : eventList){
        boolean isRegistered = service.isStudentRegisteredForEvent(student.getId(), e.getEid());
        String eventDateStr = e.getEdate(); // Event date
        String eventTimeStr = e.getStarttime(); // Event time
        boolean isEventTimePassed = false;
        try {
        	Date eventDate = dateFormat.parse(eventDateStr);
            Date eventTime = timeFormat.parse(eventTimeStr);

            // Combine date and time
            Date combinedEventDateTime = new Date(eventDate.getTime() + eventTime.getTime() - timeFormat.parse("12:00 AM").getTime());
            isEventTimePassed = currentDate.after(combinedEventDateTime);

        } catch (ParseException ex) {
        	System.out.println("ERROR : "+ex.getMessage());
            out.println("<p>Error parsing event time: " + eventDateStr + "</p>");
        }

        
    %>
    <div class="card">
        <div class="card-header">
            <%= e.getEname() %>
        </div>
        <div class="card-body">
            <p><strong>Event ID:</strong> <%= e.getEid() %></p>
            <p><strong>Domain:</strong> <%= e.getedomain() %></p>
            <p><strong>Department:</strong> <%= e.getEdepartment() %></p>
            <p><strong>Faculty ID:</strong> <%= e.getFacultyid() %></p>
            <p><strong>Faculty Name:</strong> <%= e.getFacultyname() %></p>
            <p><strong>Venue:</strong> <%= e.getEvenue() %></p>
            <p><strong>Date:</strong> <%= e.getEdate() %></p>
            <p><strong>Event Time:</strong> <%= e.getStarttime() %></p>
            <p><strong>Capacity:</strong> <%= e.getParticipants() %></p>
            <p><strong>Availability:</strong> <%= e.getAvailability() %></p>
        </div>
        <div class="card-footer">
            <%
            if (e.getAvailability() == 0) {
            %>
            <span>Not Available</span>
            <%
            } else if (isEventTimePassed) {
            %>
            <span>Event Registration Closed</span>
            <%
            } else if ("CANCELLED".equals(e.getStatus()) || "CLOSE".equals(e.getStatus())) {
            %>
            <span>Event Cancelled or Closed</span>
            <%
            } else if (isRegistered) {
            %>
            <span>Already Registered</span>
            <%
            } else {
            %>
            <a class="btn btn-outline-success" href="studentregister.jsp?eid=<%= e.getEid() %>">Register</a>
            <%
            }
            %>
        </div>
    </div>
    <% } %>
</div>

<script type="text/javascript" src="resources/js/cardssearch.js"></script>
