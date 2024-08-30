
<%@page import="com.klef.ep.models.Faculty"%>
<%@page import="com.klef.ep.models.Event"%>
<%@page import="com.klef.ep.services.AdminService"%>
<%@page import="javax.naming.InitialContext"%>
<%
InitialContext context = new InitialContext();
AdminService service = (AdminService)context.lookup("java:global/EPProject/AdminServiceImpl!com.klef.ep.services.AdminService");

Event event = new Event();
String id = request.getParameter("eid");
out.println(id);
String name = request.getParameter("ename");
String department = request.getParameter("edepartment");
String facultyid = request.getParameter("facultyid");
String etime = request.getParameter("etime");
Faculty faculty = service.viewFacultybyID(facultyid);
if(faculty==null){
	out.println("<h2> No such Faculty Found. Enter Faculty ID correctly </h2>");
}
String facultyname = faculty.getName();
String date = request.getParameter("edate");
String venue = request.getParameter("evenue");
String domain = request.getParameter("edomain");
int participants = Integer.parseInt(request.getParameter("participants"));
event.setEid(id);
event.setEname(name);
event.setEdepartment(department);
event.setFacultyid(facultyid);
event.setFacultyname(facultyname);
event.setEdate(date);
event.setEvenue(venue);
event.setParticipants(participants);
event.setedomain(domain);
event.setStarttime(etime);
System.out.println(event);
service.updateEvent(event);
response.sendRedirect("viewevents.jsf");
%>