<%@ page import="com.klef.ep.models.Student" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="com.klef.ep.services.AdminService" %>
<%@ page import="javax.naming.Context" %>
<%
    String sid = request.getParameter("sid");
    InitialContext context = new InitialContext();
    AdminService service = (AdminService)context.lookup("java:global/EPProject/AdminServiceImpl!com.klef.ep.services.AdminService");
    Student student = service.viewStudentbyID(sid);
%>
<!DOCTYPE html>
<html>

<head>
    <title>Update student</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
    <%@ include file="adminnav.jsp" %>
    <div class="container mt-3">
        
        <div class="row justify-content-center">
            <div class="col-md-8">
            	
                <div class="card">
                    <div class="card-body">
                    	<h3 class="text-center">
				            <u>Update Student ID : <%= student.getId() %></u>
				        </h3>
                        <form method="post" action="adminstudentupdation.jsp">
                        	<input type="hidden" name="sid" value="<%=student.getId() %>" /> 
                            <div class="form-group">
                                <label for="sid" class="font-weight-bold">ID</label>
                                <input type="text" id="sid" name="sid" required="true" class="form-control" value="<%= student.getId() %>" readonly="readonly" />
                            </div>

                            <div class="form-group">
                                <label for="name" class="font-weight-bold">Name</label>
                                <input type="text" id="name" name="name" required="true" class="form-control" value="<%= student.getName() %>" />
                            </div>

                            <div class="form-group">
                                <label for="gender" class="font-weight-bold">Gender</label>
                                <input type="text" name="gender" value="<%= student.getGender() %>" readonly="readonly" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label for="department" class="font-weight-bold">Department</label>
                                <input type="text" name="department" value="<%= student.getDepartment() %>" readonly="readonly" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label for="dob" class="font-weight-bold">Date Of Birth</label>
                                <input type="text" id="dob" name="dob" required="true" value="<%= student.getDob() %>" class="form-control datepicker" readonly="readonly" />
                            </div>
                            <div class="form-group">
                                <label for="email" class="font-weight-bold">Email</label>
                                <input type="email" id="email" name="email" required="true" value="<%= student.getEmail() %>" class="form-control" />
                            </div>

                            <div class="form-group">
                                <label for="contact" class="font-weight-bold">Contact</label>
                                <input type="text" id="contact" name="contact" required="true" value="<%= student.getContact() %>" class="form-control" pattern="[6789][0-9]{9}" title="Contact Number must contain 10 digits only" />
                            </div>
							<div class="form-group d-flex justify-content-center">
                                <button type="submit" class="btn btn-success mr-2">UPDATE STUDENT</button>
                                <a href="viewstudents.jsf" class="btn btn-info	">BACK</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
