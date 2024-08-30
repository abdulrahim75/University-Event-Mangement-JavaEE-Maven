<%@page import="com.klef.ep.services.AdminService"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="com.klef.ep.models.Admin"%>

<%-- <%@include file="adminnav.jsp" %> --%>
<%
Admin admin = (Admin)session.getAttribute("admin");
if(admin==null){
	response.sendRedirect("unauthorizedaccess.html");
}
InitialContext context = new InitialContext();
AdminService service = (AdminService)context.lookup("java:global/EPProject/AdminServiceImpl!com.klef.ep.services.AdminService");
long facultycount = service.getFacultyCount();
long studentcount = service.getStudentCount();
long eventcount = service.getEventCount();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home</title>
<style>
body {
    background-image: url("./images/Designer.jpeg");
    background-size: cover;
    background-attachment: fixed;
    background-repeat: no-repeat;
    margin: 0;
    font-family: Arial, sans-serif;
    color: white;
    text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.8);
}

.navbar {
    position: fixed;
    top: 0;
    width: 100%;
    z-index: 1000;
}

.container {
    text-align: center;
    background: rgba(0, 0, 0, 0.7);
    padding: 30px;
    border-radius: 15px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.3);
    max-width: 600px;
    margin: 100px auto 20px; /* Adjusted margin to account for the navbar */
}

h3 {
    font-size: 2em;
    margin-bottom: 20px;
    animation: fadeIn 2s ease-in-out;
}

.count-cards {
    display: flex;
    justify-content: space-around;
    margin-top: 20px;
    animation: fadeInUp 2s ease-in-out;
}

.count-card {
    background: rgba(255, 255, 255, 0.9);
    color: black;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
    width: 150px;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.count-card p {
    margin: 0;
    font-size: 1.2em;
    font-weight: bold;
}

.count-card:hover {
    transform: translateY(-10px);
    box-shadow: 0 15px 25px rgba(0, 0, 0, 0.3);
}

/* Animations */
@keyframes fadeIn {
    from { opacity: 0; }
    to { opacity: 1; }
}

@keyframes fadeInUp {
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
<div class="navbar">
    <%@include file="adminnav.jsp" %>
</div>
<div class="container">
    <h3>Welcome, <%= admin.getUsername() %></h3>
    <div class="count-cards">
        <div class="count-card">
            <p>Event Count</p>
            <p><%= eventcount %></p>
        </div>
        <div class="count-card">
            <p>Student Count</p>
            <p><%= studentcount %></p>
        </div>
        <div class="count-card">
            <p>Faculty Count</p>
            <p><%= facultycount %></p>
        </div>
    </div>
</div>
</body>
</html>
