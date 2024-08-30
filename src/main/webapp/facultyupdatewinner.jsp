<%@page import="java.util.Arrays"%>
<%@page import="com.klef.ep.models.RegisteredEvents"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="com.klef.ep.services.FacultyService"%>
<%
String eid = request.getParameter("eid");

String[] student = request.getParameterValues("winnerStudent");
//out.println(student[0]);

InitialContext context = new InitialContext();
FacultyService service = (FacultyService)context.lookup("java:global/EPProject/FacultyServiceImpl!com.klef.ep.services.FacultyService");
List<RegisteredEvents> eventslist = service.viewRegisteredStudents(eid);

//out.println(eventslist.get(0));

for (RegisteredEvents e : eventslist) {
    String result = service.announceWinner(eid,e.getStudentid());
    System.out.println(result);
}

response.sendRedirect("facultyviewregisteredstudents.jsp?id=" + eid);
%>
