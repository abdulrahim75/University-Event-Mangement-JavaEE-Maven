<%@page import="javax.naming.InitialContext"%>
<%@page import="com.klef.ep.services.FacultyService"%>
<%@page import="com.klef.ep.models.Faculty"%>
<%@ include file="facultynavbar.jsp" %>
<%
Faculty faculty = (Faculty)session.getAttribute("faculty");
InitialContext context = new InitialContext();
FacultyService service = (FacultyService)context.lookup("java:global/EPProject/FacultyServiceImpl!com.klef.ep.services.FacultyService");
Long count = service.getEventCount(faculty.getId());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Faculty Home</title>
<style type="text/css">
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
    color: #fff;
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
	<div class="container">
    <h3>Welcome, <%= faculty.getName() %></h3>
    <div class="count-cards">
        <div class="count-card">
            <p>Total Events Added</p>
            <p><%= count %></p>
        </div>
        
    </div>
</div>
</body>
</html>