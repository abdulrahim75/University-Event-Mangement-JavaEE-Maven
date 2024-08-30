<%@page import="com.klef.ep.services.StudentService"%>
<%@page import="com.klef.ep.models.Student"%>
<%@page import="javax.naming.InitialContext"%>
<%
InitialContext context = new InitialContext();
Student student = (Student)session.getAttribute("student");
String eid = request.getParameter("eid");
String sid = student.getId();
StudentService service = (StudentService)context.lookup("java:global/EPProject/StudentServiceImpl!com.klef.ep.services.StudentService");
service.registerToEvent(sid, eid);
response.sendRedirect("studenteventhistory.jsp");
%>