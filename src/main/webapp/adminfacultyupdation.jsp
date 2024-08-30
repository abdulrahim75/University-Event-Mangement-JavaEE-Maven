<%@page import="com.klef.ep.models.Faculty"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="com.klef.ep.services.AdminService"%>
<%
InitialContext context = new InitialContext();
AdminService service = (AdminService)context.lookup("java:global/EPProject/AdminServiceImpl!com.klef.ep.services.AdminService");
String fid = request.getParameter("fid");
String fname = request.getParameter("name");
String fgender = request.getParameter("gender");
String femail = request.getParameter("email");
String fcontact = request.getParameter("contact");
String fdepartment = request.getParameter("department");
String fdob = request.getParameter("dob");
Faculty faculty = new Faculty();
faculty.setId(fid);
faculty.setName(fname);
faculty.setGender(fgender);
faculty.setDob(fdob);
faculty.setContact(fcontact);
faculty.setEmail(femail);
faculty.setDepartment(fdepartment);
service.updateFaculty(faculty);
response.sendRedirect("adminviewfaculty.jsp?fid="+fid);
%>