<%@page import="com.klef.ep.models.Faculty"%>
<%@include file="facultynavbar.jsp" %>
<%
Faculty faculty = (Faculty)session.getAttribute("faculty");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Event</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="./resources/js/datepicker.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h3 class="text-center mb-4"><u>Register an Event</u></h3>
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-body">
                        <form id="eventForm" action="facultyeventinsertion.jsp" method="post" class="form">
                            <div class="form-group row">
                                <label for="ename" class="col-sm-3 col-form-label">Event Name</label>
                                <div class="col-sm-9">
                                    <input type="text" id="ename" name="ename" class="form-control" required />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="edepartment" class="col-sm-3 col-form-label">Event Department</label>
                                <div class="col-sm-9">
                                    <input type="text" id="edepartment" name="edepartment" class="form-control" value="<%=faculty.getDepartment() %>" disabled />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="edomain" class="col-sm-3 col-form-label">Event Domain</label>
                                <div class="col-sm-9">
                                    <select id="edomain" name="edomain" class="form-control" required>
                                        <option value="">--Select--</option>
                                        <option value="TECHNICAL">Technical</option>
                                        <option value="CULTURAL">Cultural</option>
                                        <option value="LIBERAL ARTS">Liberal Arts</option>
                                        <option value="INNOVATION AND INCUBATION">Innovation and Incubation</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="edate" class="col-sm-3 col-form-label">Event Date</label>
                                <div class="col-sm-9">
                                    <input type="text" id="edate" name="edate" class="form-control datepicker" required />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="etime" class="col-sm-3 col-form-label">Event Time</label>
                                <div class="col-sm-9">
                                    <input type="text" id="etime" name="etime" class="form-control" pattern="\b(0[1-9]|1[0-2]):[0-5][0-9] [AP]M\b" title="Time Must Be in HH:MM AM/PM" required />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="facultyid" class="col-sm-3 col-form-label">Enter Faculty ID</label>
                                <div class="col-sm-9">
                                    <input type="text" id="facultyid" name="facultyid" class="form-control" value="<%=faculty.getId() %>" disabled />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="evenue" class="col-sm-3 col-form-label">Event Venue</label>
                                <div class="col-sm-9">
                                    <input type="text" id="evenue" name="evenue" class="form-control" required />
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="participants" class="col-sm-3 col-form-label">No of Participants</label>
                                <div class="col-sm-9">
                                    <input type="text" id="participants" name="participants" class="form-control" required />
                                </div>
                            </div>
                            <div class="form-group d-flex justify-content-center">
                                <button type="submit" class="btn btn-success mr-2">ADD EVENT</button>
                                <button type="reset" class="btn btn-info mr-2" >CLEAR</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
