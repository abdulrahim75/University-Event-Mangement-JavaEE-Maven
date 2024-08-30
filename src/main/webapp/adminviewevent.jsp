<%@page import="com.klef.ep.services.AdminService"%>
<%@page import="com.klef.ep.models.RegisteredEvents"%>
<%@page import="com.klef.ep.models.Event"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="com.klef.ep.models.Student"%>
<%@include file="adminnav.jsp"%>
<%
String eid = request.getParameter("eid");
InitialContext context = new InitialContext();
AdminService service = (AdminService) context.lookup("java:global/EPProject/AdminServiceImpl!com.klef.ep.services.AdminService");
Event event1 = service.vieweventbyID(eid);
String eventwinner = service.getEventWinner(eid);
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>View Event</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>

label{
 font-weight: bolder;
}

.event-card {
    background: #fff;
    border-radius: 12px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1), 0 8px 16px rgba(0, 0, 0, 0.1);
    padding: 30px;
    width: 100%;
    max-width: 500px;
    text-align: center;
    transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
}

.event-card:hover {
    transform: translateY(-10px);
    box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15), 0 12px 24px rgba(0, 0, 0, 0.15);
}

.event-card h4 {
    font-size: 24px;
    color: #007bff;
    margin-bottom: 20px;
    text-transform: uppercase;
    animation: fadeIn 1s ease-in-out;
}

.event-card p {
    font-size: 18px;
    color: #666;
    margin: 10px 0;
    animation: fadeIn 1.5s ease-in-out;
}

@keyframes fadeIn {
    from {
        opacity: 0;
        transform: translateY(20px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

</style>
</head>
<body>
	<div class="container mt-5" align="center">
		<div class="event-card">
            <h4>
                <%=event1.getEname()%></h4>
            <p>
                <label>EVENT ID : </label><%=event1.getEid()%>
            </p>
            <p>
               <label> EVENT DEPARTMENT : </label><%=event1.getEdepartment()%>
            </p>
            <p>
               <label> EVENT DOMAIN : </label><%=event1.getedomain() %>
            </p>
            <p>
               <label> EVENT VENUE : </label><%=event1.getEvenue() %>
            </p>
            <p>
               <label> EVENT FACULTY : </label><%=event1.getFacultyname()%>
            </p>
            <p>
               <label> EVENT CAPACITY : </label><%=event1.getParticipants()%>
            </p>
            <p>
               <label>CURRENT AVAILABILITY : </label> <%=event1.getAvailability()%>
            </p>
            <p>
               <label> EVENT WINNER : </label> <%=eventwinner%>
            </p>
            <a class="btn btn-info" href="viewevents.jsf" >BACK</a>
        </div>
	</div>

</html>
