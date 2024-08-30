<%@page import="java.util.Arrays"%>
<%@page import="com.klef.ep.models.RegisteredEvents"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="com.klef.ep.services.AdminService"%>
<%
String eid = request.getParameter("eid");

String[] attendedStudents = request.getParameterValues("attendedStudents");
out.println(attendedStudents[0]);

InitialContext context = new InitialContext();
AdminService service = (AdminService)context.lookup("java:global/EPProject/AdminServiceImpl!com.klef.ep.services.AdminService");
List<RegisteredEvents> eventslist = service.viewRegisteredStudents(eid);

for (RegisteredEvents e : eventslist) {
	String status = "NOT_ATTENDED"; // Default status
    if (attendedStudents != null && Arrays.asList(attendedStudents).contains(e.getStudentid())) {
        status = "ATTENDED"; // Set to ATTENDED if the student ID is in the attendedStudents array
    }
    service.setStatus(eid, e.getStudentid(), status);
}

response.sendRedirect("adminviewregisteredstudents.jsp?id=" + eid);
%>