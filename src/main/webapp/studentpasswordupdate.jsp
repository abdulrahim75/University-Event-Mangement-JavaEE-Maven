<%@page import="com.klef.ep.models.Student"%>
<%@page import="com.klef.ep.services.StudentService"%>
<%@page import="javax.naming.InitialContext"%>
<%
System.out.println("Request Received");

InitialContext context = new InitialContext();
StudentService service = (StudentService) context.lookup("java:global/EPProject/StudentServiceImpl!com.klef.ep.services.StudentService");

String newpass = request.getParameter("newpass");
String oldpass = request.getParameter("currpass");
String confnewpass = request.getParameter("confnewpass");

Student student = (Student) session.getAttribute("student");
if (student != null) {
    String id = student.getId();
    if (newpass.equals(student.getPassword())) {
        %>
        <h3 align="center" style="color: red;">New Password And Current Password are the Same</h3>
        <a href="studentprofile.jsp">Try Again</a>
        <% 
    } else if (newpass.equals(confnewpass) && student.getPassword().equals(oldpass)) {
        service.updatepassword(id, newpass);
        Student newStudent = service.getprofile(student.getId());
        session.setAttribute("student", newStudent);
        System.out.println("Password Updated");
        response.sendRedirect("studentprofile.jsp");
    } else {
        %>
        <h3 align="center" style="color: red;">Current Password is Incorrect or New Password Didn't Match with Confirm New Password.</h3>
        <a href="studentprofile.jsp">Try Again</a>
        <%
    }
}
%>
