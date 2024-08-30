<%@page import="com.klef.ep.models.Event"%>
<%@page import="com.klef.ep.models.Faculty"%>
<%@page import="java.util.Random"%>
<%@page import="com.klef.ep.services.FacultyService"%>
<%@page import="javax.naming.InitialContext"  %>

<%
Faculty faculty = (Faculty)session.getAttribute("faculty");
InitialContext context = new InitialContext();
FacultyService service = (FacultyService)context.lookup("java:global/EPProject/FacultyServiceImpl!com.klef.ep.services.FacultyService");
Random random = new Random();
String name = request.getParameter("ename");
String department = faculty.getDepartment();
String facultyid = faculty.getId();
String date = request.getParameter("edate");
String time = request.getParameter("etime");
String venue = request.getParameter("evenue");
String domain = request.getParameter("edomain");
int participants = Integer.parseInt(request.getParameter("participants"));
String id = Integer.toString(100000 + random.nextInt(900000));
String facultyname = faculty.getName();
Event event = new Event();
event.setEid(id);
event.setEname(name);
event.setEdepartment(department);
event.setFacultyid(facultyid);
event.setFacultyname(facultyname);
event.setEdate(date);
event.setEvenue(venue);
event.setParticipants(participants);
event.setedomain(domain);
event.setAvailability(participants);
event.setStatus("OPEN");
event.setStarttime(time);
//System.out.println(event);
service.registerAnEvent(event);
response.sendRedirect("facultyeventhistory.jsp");
%>