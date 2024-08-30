<%@page import="com.klef.ep.services.FacultyService"%>
<%@page import="com.klef.ep.models.Faculty"%>
<%@page import="javax.naming.InitialContext"%>
<%@ include file="facultynavbar.jsp" %>
<%
InitialContext context = new InitialContext();
FacultyService service = (FacultyService)context.lookup("java:global/EPProject/FacultyServiceImpl!com.klef.ep.services.FacultyService");
Faculty faculty = (Faculty)session.getAttribute("faculty");
Long attendedCount = service.getAttendedCount(faculty.getId());
Long notattendedCount = service.getNonAttendedCount(faculty.getId());
Long eventCount = service.getEventCount(faculty.getId());
%>
<head>
<title>Faculty Analysis</title>
<style>
  body {
    background-color: #f4f4f4;
    font-family: Arial, sans-serif;
  }
  .card-container {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
    gap: 20px;
    padding: 20px;
  }
  .card {
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    padding: 20px;
    margin: 20px;
    flex: 1 1 calc(45% - 40px); /* Ensure cards are nearly equal in width */
    max-width: 500px;
  }
  .chart-container, .bar-chart-container {
    width: 100%; /* Ensure canvas uses the full width of the card */
    height: 400px; /* Set a fixed height for both charts */
  }
</style> 
<script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.3/dist/chart.umd.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.1.0"></script>
</head>
<body>
	<h3 align="center" ><u>Analysis</u></h3>
  <div class="card-container">	
   
    <div class="card">
      <canvas id="attendanceChart" class="chart-container"></canvas>
    </div>
    <div class="card">
      <canvas id="eventChart" class="bar-chart-container"></canvas>
    </div>
  </div>

  <script>
    document.addEventListener('DOMContentLoaded', function () {
      const ctx1 = document.getElementById('attendanceChart').getContext('2d');
      const attendanceChart = new Chart(ctx1, {
        type: 'pie',
        data: {
          labels: ['Attended', 'Not Attended'],
          datasets: [{
            data: [<%= attendedCount %>, <%= notattendedCount %>],
            backgroundColor: ['#36a2eb', '#ff6384'],
            hoverBackgroundColor: ['#36a2eb', '#ff6384']
          }]
        },
        options: {
          responsive: true,
          plugins: {
            title: {
              display: true,
              text: 'EVENT ATTENDENCE ANALYSIS'
            },
            datalabels: {
              formatter: (value, ctx) => {
                let sum = 0;
                let dataArr = ctx.chart.data.datasets[0].data;
                dataArr.map(data => {
                  sum += data;
                });
                let percentage = (value * 100 / sum).toFixed(2) + "%";
                return percentage;
              },
              color: '#fff',
            }
          }
        },
        plugins: [ChartDataLabels]
      });

      const ctx2 = document.getElementById('eventChart').getContext('2d');
      const eventChart = new Chart(ctx2, {
        type: 'bar',
        data: {
          labels: ['Total Events'],
          datasets: [{
            label: 'Event Count',
            data: [<%= eventCount %>],
            backgroundColor: ['#42a5f5'],
            hoverBackgroundColor: ['#1e88e5']
          }]
        },
        options: {
          responsive: true,
          plugins: {
            title: {
              display: true,
              text: 'EVENT BAR CHART'
            },
            datalabels: {
              anchor: 'end',
              align: 'start', // Align the labels at the start of the bars
              offset: 20, // Negative offset to place labels inside the bars
              formatter: (value) => {
                return value;
              },
              color: '#fff', // Change label color to contrast with the bar color
              font: {
                weight: 'bold'
              }
            }
          }, 
          scales: {
            y: {
              beginAtZero: true
            }
          }
        },
        plugins: [ChartDataLabels]
      });
    });
  </script>
</body>
