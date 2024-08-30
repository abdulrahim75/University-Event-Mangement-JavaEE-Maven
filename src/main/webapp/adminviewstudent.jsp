<%@page import="com.klef.ep.services.AdminService"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="com.klef.ep.models.Student"%>
<%@include file="adminnav.jsp" %>
<%
String sid = request.getParameter("sid");
InitialContext context = new InitialContext();
AdminService service = (AdminService)context.lookup("java:global/EPProject/AdminServiceImpl!com.klef.ep.services.AdminService");
Student student = service.viewStudentbyID(sid);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Profile</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<style>
  body {
    background-color: #f8f9fa;
    font-family: Arial, sans-serif;
  }
  .profile-card {
    background: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    padding: 20px;
    width: 350px;
    margin: 20px auto;
    text-align: center;
  }
  .profile-image img {
    width: 150px;
    height: 150px;
    border-radius: 50%;
    border: 4px solid #007bff;
    margin-bottom: 15px;
  }
  .dialogue-box {
    display: none;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    border: 1px solid #ccc;
    padding: 20px;
    background-color: white;
    z-index: 1000;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    width: 300px;
  }
  .overlay {
    display: none;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 999;
  }
  .btn-custom {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  .btn-custom:hover {
    background-color: #0056b3;
  }
  .btn-container {
    display: flex;
    justify-content: space-around;
    margin-top: 15px;
  }
  .form-control {
    margin-bottom: 10px;
  }
  .form-label {
    margin-bottom: 5px;
    text-align: left;
    width: 100%;
  }
</style>
</head>
<body>
  <div class="profile-card">
    <div class="profile-image">
      <img id="image" alt="Profile Picture" src="http://localhost:2014/EPProject/images/profiles/student/<%=student.getProfile()%>"/>
    </div>
    <h4><%=student.getName() %></h4>
    <p>ID: <%=student.getId() %></p>
    <p>Department: <%=student.getDepartment() %></p>
    <p>Gender: <%=student.getGender() %></p>
    <p>Date of Birth: <%=student.getDob() %></p>
    <p>Contact: <%=student.getContact() %></p>
    <p>Email: <%=student.getEmail() %></p>
    <a href="viewstudents.jsf" class="btn btn-secondary">BACK</a>
  </div>
 
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
