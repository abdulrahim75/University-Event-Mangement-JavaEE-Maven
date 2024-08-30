<%@page import="com.klef.ep.services.AdminService"%>
<%@page import="javax.naming.InitialContext"%>
<%@include file="adminnav.jsp" %>
<%
    InitialContext context = new InitialContext();
    AdminService service = (AdminService)context.lookup("java:global/EPProject/AdminServiceImpl!com.klef.ep.services.AdminService");
    Long attendingCount = service.getAttendedCount();
    Long notAttendingCount = service.getNonAttendedCount();
    Long facultyCount = service.getFacultyCount();
    Long studentCount = service.getStudentCount();
    Long eventCount = service.getEventCount();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Analysis</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.3/dist/chart.umd.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.1.0"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
        }
        .chart-container {
            width: 45%;
            margin-bottom: 20px;
        }
        .chart-container canvas {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            margin-bottom: 40px;
        }
        h2 {
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>Admin Analysis</h1>
    <div class="container">
        <div class="chart-container">
            <h2>Counts</h2>
            <canvas id="barChart" width="400" height="200"></canvas>
        </div>
        <div class="chart-container">
            <h2>Attendance</h2>
            <canvas id="pieChart" width="400" height="200"></canvas>
        </div>
        <div class="chart-container">
            <h2>Comparison</h2>
            <canvas id="comparisonChart" width="400" height="200"></canvas>
        </div>
        <div class="chart-container">
            <h2>Events Scatter</h2>
            <canvas id="scatterChart" width="400" height="200"></canvas>
        </div>
    </div>
    
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            // Bar Chart
            var barCtx = document.getElementById('barChart').getContext('2d');
            var barChart = new Chart(barCtx, {
                type: 'bar',
                data: {
                    labels: ['Students', 'Faculty', 'Events'],
                    datasets: [{
                        label: 'Count',
                        data: [<%= studentCount %>, <%= facultyCount %>, <%= eventCount %>],
                        backgroundColor: ['rgba(75, 192, 192, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)'],
                        borderColor: ['rgba(75, 192, 192, 1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)'],
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    },
                    plugins: {
                        datalabels: {
                            anchor: 'end',
                            align: 'top',
                            formatter: Math.round,
                            font: {
                                weight: 'bold'
                            }
                        }
                    }
                }
            });

            // Pie Chart
            var pieCtx = document.getElementById('pieChart').getContext('2d');
            var pieChart = new Chart(pieCtx, {
                type: 'pie',
                data: {
                    labels: ['Attending', 'Not Attending'],
                    datasets: [{
                        label: 'Attendance',
                        data: [<%= attendingCount %>, <%= notAttendingCount %>],
                        backgroundColor: ['rgba(75, 192, 192, 0.2)', 'rgba(255, 99, 132, 0.2)'],
                        borderColor: ['rgba(75, 192, 192, 1)', 'rgba(255, 99, 132, 1)'],
                        borderWidth: 1
                    }]
                },
                options: {
                    plugins: {
                        datalabels: {
                            color: '#fff',
                            formatter: (value, ctx) => {
                                let sum = 0;
                                let dataArr = ctx.chart.data.datasets[0].data;
                                dataArr.map(data => {
                                    sum += data;
                                });
                                let percentage = (value * 100 / sum).toFixed(2) + "%";
                                return percentage;
                            },
                        }
                    }
                }
            });

            // Comparison Chart
            var comparisonCtx = document.getElementById('comparisonChart').getContext('2d');
            var comparisonChart = new Chart(comparisonCtx, {
                type: 'bar',
                data: {
                    labels: ['Students', 'Faculty'],
                    datasets: [{
                        label: 'Count',
                        data: [<%= studentCount %>, <%= facultyCount %>],
                        backgroundColor: ['rgba(153, 102, 255, 0.2)', 'rgba(255, 159, 64, 0.2)'],
                        borderColor: ['rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)'],
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    },
                    plugins: {
                        datalabels: {
                            anchor: 'end',
                            align: 'top',
                            formatter: Math.round,
                            font: {
                                weight: 'bold'
                            }
                        }
                    }
                }
            });

            // Scatter Chart
            var scatterCtx = document.getElementById('scatterChart').getContext('2d');
            var scatterChart = new Chart(scatterCtx, {
                type: 'scatter',
                data: {
                    datasets: [{
                        label: 'Events',
                        data: [
                            { x: 10, y: 20 },
                            { x: 20, y: 30 },
                            { x: 30, y: 40 },
                            { x: 40, y: 50 },
                            { x: 50, y: 60 },
                        ],
                        backgroundColor: 'rgba(255, 99, 132, 0.2)',
                        borderColor: 'rgba(255, 99, 132, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        x: {
                            type: 'linear',
                            position: 'bottom'
                        }
                    },
                    plugins: {
                        datalabels: {
                            anchor: 'end',
                            align: 'top',
                            color: '#fff',
                            formatter: (value, ctx) => {
                                return `(${value.x}, ${value.y})`;
                            },
                            font: {
                                weight: 'bold'
                            }
                        }
                    }
                }
            });
        });
    </script>
</body>
</html>
