<%@page import="com.klef.ep.models.RegisteredEvents"%>
<%@page import="com.klef.ep.models.Event"%>
<%@page import="com.klef.ep.services.StudentService"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="com.klef.ep.models.Student"%>
<%@include file="studentnav.jsp" %>
<%
String eid = request.getParameter("eid");
Student student = (Student)session.getAttribute("student");
InitialContext context = new InitialContext();
StudentService service = (StudentService)context.lookup("java:global/EPProject/StudentServiceImpl!com.klef.ep.services.StudentService");
Event event1 = service.viewEventByID(eid);
RegisteredEvents event2 = service.viewregisteredEventbyID(student.getId(), eid);
%>
<head>
<title>View Event</title>
<style type="text/css">
    body {
        font-family: Arial, sans-serif;
        background-color: #f8f9fa;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: calc(100vh - 60px);
        padding-top: 60px;
    }
    .nav{
        position: fixed;
        top: 0;
        width:85%;
        color: white;
        text-align: center;
        z-index: 1000; 
        padding: 15px;
		background-color: rgba(0, 0, 0, 0.85);
		box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
		border-radius: 8px;
		margin: 20px;
    }
    label{
        font-weight: bolder;
    }
    .event-card {
        background: #fff;
        border-radius: 12px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1), 0 8px 16px rgba(0, 0, 0, 0.1);
        padding: 30px;
        width: 100%;
        max-width: 500px;
        text-align: center;
        transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
    }
    .event-card:hover {
        transform: translateY(-10px);
        box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15), 0 12px 24px rgba(0, 0, 0, 0.15);
    }
    .event-card h4 {
        font-size: 24px;
        color: #007bff;
        margin-bottom: 20px;
        text-transform: uppercase;
        animation: fadeIn 1s ease-in-out;
    }
    .event-card p {
        font-size: 18px;
        color: #666;
        margin: 10px 0;
        animation: fadeIn 1.5s ease-in-out;
    }
    @keyframes fadeIn {
        from {
            opacity: 0;
            transform: translateY(20px);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }
    .event-card button {
        background-color: #f39c12;
        border: none;
        color: #fff;
        padding: 15px 30px;
        font-size: 18px;
        border-radius: 8px;
        cursor: pointer;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        transition: all 0.3s ease;
    }
    .event-card button:hover {
        background-color: #e67e22;
        box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
    }
    .event-card button:active {
        transform: translateY(2px);
        box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1);
    }
    #animation {
        font-size: 24px;
        color: #28a745;
        margin-top: 20px;
    }
    .hidden {
        display: none;
    }
    @keyframes winning-animation {
        0% {
            transform: scale(1);
            background-color: #f39c12;
        }
        50% {
            transform: scale(1.1);
            background-color: #f1c40f;
        }
        100% {
            transform: scale(1);
            background-color: #f39c12;
        }
    }
    .winning-animation {
        animation: winning-animation 0.5s ease-in-out;
    }
</style>
</head>
<body>
    <div class="container mt-5" align="center">
        <div class="event-card">
            <h4><%=event1.getEname()%></h4>
            <p><label>EVENT ID : </label><%=event1.getEid()%></p>
            <p><label>EVENT DEPARTMENT : </label><%=event1.getEdepartment()%></p>
            <p><label>EVENT DOMAIN : </label><%=event1.getedomain()%></p> 
            <p><label>EVENT VENUE : </label><%=event1.getEvenue()%></p>
            <p><label>EVENT FACULTY : </label><%=event1.getFacultyname()%></p>
            <p><label>EVENT STATUS :</label> <%=event2.getStatus()%></p>
            <p><label>WINNER OR NOT :</label> <%=event2.getWinstatus()%></p>
            
            <%
            if ("ATTENDED".equals(event2.getStatus())) {
            %>
                <form action="studentdownloadparticationcertificate.jsp" method="post" class="download-form">
                    <input type="hidden" name="studentName" value="<%=student.getName()%>">
                    <input type="hidden" name="eventName" value="<%=event1.getEname()%>">
                    <input type="hidden" name="eid" value="<%=event1.getEid()%>">
                    <button type="submit" class="download-button">Download Participation Certificate</button>
                </form>
            <%
            }
            %>
            <br><br>
            <%
            if ("YES".equals(event2.getWinstatus())) {
            %>
                <form action="studentdownloadcertificate.jsp" method="post" class="download-form">
                    <input type="hidden" name="studentName" value="<%=student.getName()%>">
                    <input type="hidden" name="eventName" value="<%=event1.getEname()%>">
                    <input type="hidden" name="eid" value="<%=event1.getEid()%>">
                    <button type="submit" class="download-button">Download Achievement Certificate</button>
                </form>
            <%
            }
            %>
            <div id="animation" class="hidden">&#127881; Congratulations! &#127881;</div>
        </div>
    </div>

    <script type="text/javascript">
        document.querySelectorAll(".download-button").forEach(function(button) {
            button.addEventListener("click", function(event) {
                event.preventDefault(); // Prevent the form from submitting immediately
                var button = event.target;
                button.classList.add("winning-animation");

                // Show the animation text
                var animationText = document.getElementById("animation");
                animationText.classList.remove("hidden");

                // Wait for the animation to complete before submitting the form
                setTimeout(function() {
                    button.closest(".download-form").submit();
                }, 500); // Match the duration of the animation
            });
        });
    </script>
</body>
