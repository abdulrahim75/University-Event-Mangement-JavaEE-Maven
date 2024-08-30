<%@page import="com.klef.ep.models.Admin"%>
<%@page import="com.klef.ep.models.Faculty"%>
<%@page import="java.util.List"%>
<%@ page import="com.klef.ep.models.Event" %>
<%@ page import="com.klef.ep.services.AdminService" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ include file="adminnav.jsp" %>
<%
Admin admin = (Admin)session.getAttribute("admin");
if(admin==null){
	response.sendRedirect("unauthorizedaccess.html");
}
    String tmp = request.getParameter("id");
    InitialContext context = new InitialContext();
    AdminService service = (AdminService) context.lookup("java:global/EPProject/AdminServiceImpl!com.klef.ep.services.AdminService");
    Event event = service.vieweventbyID(tmp);
    List<Faculty> facultyList = service.viewFaculty();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Update Event</title> 
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="./resources/js/datepicker.js"></script>
</head>
<body>
    <div class="container mt-3"> 
        <h3 class="text-center"><u>Update Event</u></h3>
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-body">
                        <form id="eventForm" action="admineventupdation.jsp" method="post">
                            <div class="form-group">
                                <label for="eid" class="font-weight-bold">Event ID</label>
                                <input type="text" id="eid" name="eid" class="form-control" value="<%= event.getEid() %>" readonly="readonly" required />
                            </div>
                            <div class="form-group">
                                <label for="ename" class="font-weight-bold">Event Name</label>
                                <input type="text" id="ename" name="ename" class="form-control" value="<%= event.getEname() %>" readonly="readonly" required />
                            </div>
                            <div class="form-group">
                                <label for="edepartment" class="font-weight-bold">Event Department</label>
                                <input type="text" id="edepartment" name="edepartment" class="form-control" value="<%= event.getEdepartment() %>" readonly="readonly" required />
                            </div>
                            <div class="form-group">
                                <label for="edomain" class="font-weight-bold">Event Domain</label>
                                <input type="text" id="edomain" name="edomain" class="form-control" value="<%= event.getedomain() %>" readonly="readonly" required />
                            </div>
                            <div class="form-group">
                                <label for="edate" class="font-weight-bold">Event Date</label>
                                <input type="text" id="edate" name="edate" class="form-control datepicker" value="<%= event.getEdate() %>" required />
                            </div>
                            <div class="form-group">
                                <label for="etime" class="font-weight-bold">Event Date</label>
                                <input type="text" id="etime" name="etime" class="form-control" pattern="\b(0[1-9]|1[0-2]):[0-5][0-9] [AP]M\b" title="Time Must Be in HH:MM AM/PM" value="<%= event.getStarttime() %>" required />
                            </div>
                            <div class="form-group">
                                <label for="facultyid" class="font-weight-bold">Select Faculty ID</label>
                                <select id="facultyid" name="facultyid" class="form-control" required>
                                    <option value="">Select Faculty ID</option>
                                    <% for (Faculty faculty : facultyList) { %>
                                        <option value="<%= faculty.getId() %>" <%= faculty.getId().equals(event.getFacultyid()) ? "selected" : "" %>><%= faculty.getId() %> - <%= faculty.getName() %> - <%=faculty.getDepartment() %> </option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="evenue" class="font-weight-bold">Event Venue</label>
                                <input type="text" id="evenue" name="evenue" class="form-control" value="<%= event.getEvenue() %>" required />
                            </div>
                            <div class="form-group">
                                <label for="participants" class="font-weight-bold">No of Participants</label>
                                <input type="number" id="participants" name="participants" class="form-control" value="<%= event.getParticipants() %>" required />
                            </div>
                            <button type="submit" class="btn btn-primary btn-block">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
