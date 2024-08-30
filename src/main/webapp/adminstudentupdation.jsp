<%@page import="com.klef.ep.models.Admin"%>
<%@page import="com.klef.ep.models.Student"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="com.klef.ep.services.AdminService"%>
<%
Admin admin = (Admin)session.getAttribute("admin");
if(admin==null){
	response.sendRedirect("unauthorizedaccess.html");
}
InitialContext context = new InitialContext();
AdminService service = (AdminService)context.lookup("java:global/EPProject/AdminServiceImpl!com.klef.ep.services.AdminService");
String sid = request.getParameter("sid");
String sname = request.getParameter("name");
String sgender = request.getParameter("gender");
String semail = request.getParameter("email");
String scontact = request.getParameter("contact");
String sdepartment = request.getParameter("department");
String sdob = request.getParameter("dob");
Student student = new Student();
student.setId(sid);
student.setName(sname);
student.setGender(sgender);
student.setDob(sdob);
student.setContact(scontact);
student.setEmail(semail);
student.setDepartment(sdepartment);
service.updateStudent(student);
response.sendRedirect("adminviewstudent.jsp?sid="+sid);
%>