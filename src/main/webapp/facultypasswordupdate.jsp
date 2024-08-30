
<%@page import="com.klef.ep.models.Faculty"%>
<%@page import="com.klef.ep.services.FacultyService"%>
<%@page import="javax.naming.InitialContext"%>
<%
InitialContext context = new InitialContext();
FacultyService service = (FacultyService)context.lookup("java:global/EPProject/FacultyServiceImpl!com.klef.ep.services.FacultyService");
String newpass = request.getParameter("newpass");
String oldpass = request.getParameter("currpass");
String confnewpass = request.getParameter("confnewpass");
Faculty faculty = (Faculty)session.getAttribute("faculty");
if (faculty != null) {
    String id = faculty.getId();
    if (newpass.equals(faculty.getPassword())) {
        %>
        <h3 align="center" style="color: red;">New Password And Current Password are the Same</h3>
        <a href="facultyprofile.jsp">Try Again</a>
        <% 
    } else if (newpass.equals(confnewpass) && faculty.getPassword().equals(oldpass)) {
        service.updatePassword(id, newpass);
        Faculty newfaculty = service.getProfile(faculty.getId());
        session.setAttribute("faculty", newfaculty);
        System.out.println("Password Updated");
        response.sendRedirect("facultyprofile.jsp");
    } else {
        %>
        <h3 align="center" style="color: red;">Current Password is Incorrect or New Password Didn't Match with Confirm New Password.</h3>
        <a href="facultyprofile.jsp">Try Again</a>
        <%
    }
}
%>