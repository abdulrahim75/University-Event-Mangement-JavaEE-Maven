<!DOCTYPE html>
<html>
<head>
    <title>Admin Navigation</title>
    <link rel="stylesheet" href="resources/css/adminnavcss.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"/>
    <style>
        body {
            background-image: url("./images/Designer.jpeg");
            background-size: cover;
            background-position: fixed;
            background-repeat: no-repeat;
            margin: 0;
            font-family: Arial, sans-serif;
        }

        .nav {
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 15px;
            background-color: rgba(0, 0, 0, 0.85);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
            border-radius: 8px;
            margin: 20px;
            position: relative;
        }

        .nav-link {
            color: #fff;
            text-decoration: none;
            font-weight: bold;
            margin: 0 15px;
            position: relative;
            transition: color 0.3s;
        }

        .nav-link:hover {
            color: #f39c12;
      transform: scale(1.05);
        }

        .nav-dropdown {
            position: relative;
            display: inline-block;
        }

        .nav-dropdown-content {
            display: none;
            position: absolute;
            background-color: rgba(0, 0, 0, 0.85);
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
            border-radius: 8px;
        }

        .nav-dropdown-content a {
            color: #fff;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        .nav-dropdown-content a:hover {
            background-color: #575757;
        }

        .nav-dropdown:hover .nav-dropdown-content {
            display: block;
        }
    </style>
</head>
<body>
    <nav class="nav">
        <a href="adminhome.jsp" class="nav-link"><i class="fa fa-home" aria-hidden="true"></i>&nbsp;Home</a>

        <div class="nav-dropdown">
            <a href="#" class="nav-link"><i class="fa fa-graduation-cap"></i>&nbsp; Student</a>
            <div class="nav-dropdown-content" align="center">
                <a href="addStudent.jsf"><i class="fa fa-plus"></i>&nbsp;Add Student</a>
                <a href="viewstudents.jsf"><i class="fas fa-user-graduate"  ></i>&nbsp;View Students</a>
            </div>
        </div>
     
        <div class="nav-dropdown">
            <a href="#" class="nav-link"><i class="fas fa-chalkboard-teacher"></i>&nbsp; Faculty</a>
            <div class="nav-dropdown-content" align="center">
                <a href="addFaculty.jsf"><i class="fa fa-plus"></i>&nbsp;Add Faculty</a>
                <a href="viewfaculty.jsf"><i class="fa-solid fa-user"></i>&nbsp;View Faculties</a>
            </div>
        </div>

        <div class="nav-dropdown">
            <a href="#" class="nav-link"><i class="fa fa-calendar"></i>&nbsp; Event</a>
            <div class="nav-dropdown-content" align="center">
                <a href="addEvent.jsf"><i class="fa fa-plus"></i>&nbsp;Add Event</a>
                <a href="viewevents.jsf"><i class="fa fa-calendar"></i>&nbsp;View Events</a>
            </div>
        </div>
		<a href="adminanalysis.jsp" class="nav-link"><i class="fa fa-bar-chart" aria-hidden="true"></i>&nbsp;Analysis</a>
        <a href="adminlogout.jsp" class="nav-link"><i class="fa fa-sign-out" aria-hidden="true"></i>&nbsp;Logout</a>
    </nav>
</body>
</html>
