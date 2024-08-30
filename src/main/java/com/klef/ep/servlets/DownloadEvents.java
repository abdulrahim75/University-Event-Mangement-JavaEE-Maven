package com.klef.ep.servlets;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.klef.ep.services.AdminService;

/**
 * Servlet implementation class DownloadEvents
 */
@WebServlet("/downloadevents")
public class DownloadEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
			response.setContentType("text/csv");
	        response.setHeader("Content-Disposition", "attachment;filename=event_data.csv");
	        InitialContext context = new InitialContext();
	        AdminService service = (AdminService)context.lookup("java:global/EPProject/AdminServiceImpl!com.klef.ep.services.AdminService");
	        String csvData = service.downloadEvents();
	        response.getWriter().write(csvData);
        }
        catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

}
