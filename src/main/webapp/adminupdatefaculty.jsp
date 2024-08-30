<%@ page import="com.klef.ep.models.Faculty" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="com.klef.ep.services.AdminService" %>
<%@ page import="javax.naming.Context" %>
<%
    String fid = request.getParameter("fid");
    InitialContext context = new InitialContext();
    AdminService service = (AdminService)context.lookup("java:global/EPProject/AdminServiceImpl!com.klef.ep.services.AdminService");
    Faculty faculty = service.viewFacultybyID(fid);
%>
<!DOCTYPE html>
<html>

<head>
    <title>Update Faculty</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
    <%@ include file="adminnav.jsp" %>
    <div class="container mt-3">
        <h3 class="text-center">
            <u>Update Faculty ID : <%= faculty.getId() %></u>
        </h3>
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-body">
                        <form method="post" action="adminfacultyupdation.jsp">
                        	<div class="form-group">
                                <label for="fid" class="font-weight-bold">ID</label>
                                <input type="text" id="fid" name="fid" required="true" class="form-control" value="<%= faculty.getId() %>" readonly="readonly" />
                            </div>
                            
                            <div class="form-group">
                                <label for="name" class="font-weight-bold">Name</label>
                                <input type="text" id="name" name="name" required="true" class="form-control" value="<%= faculty.getName() %>" />
                            </div>

                            <div class="form-group">
                                <label for="gender" class="font-weight-bold">Gender</label>
                                <input name="gender" type="text" value="<%= faculty.getGender() %>" readonly="readonly" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label for="department" class="font-weight-bold">Department</label>
                                <input type="text" value="<%= faculty.getDepartment() %>" readonly="readonly" name="department" class="form-control" />
                     
                            </div>
                            <div class="form-group">
                                <label for="dob" class="font-weight-bold">Date Of Birth</label>
                                <input type="text" id="dob" name="dob" required="true" value="<%= faculty.getDob() %>" class="form-control datepicker" readonly="readonly" />
                                
                            </div>
                            <div class="form-group">
                                <label for="email" class="font-weight-bold">Email</label>
                                <input type="email" id="email" name="email" required="true" value="<%= faculty.getEmail() %>" class="form-control" />
                                
                            </div>

                            <div class="form-group">
                                <label for="contact" class="font-weight-bold">Contact</label>
                                <input type="text" id="contact" name="contact" required="true" value="<%= faculty.getContact() %>" class="form-control" pattern="[6789][0-9]{9}" title="Contact Number must contain 10 digits only" />
                               
                            </div>

                            <div class="form-group">
                                <button type="submit" class="btn btn-primary btn-block">UPDATE FACULTY</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
