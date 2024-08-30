package com.klef.ep.servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.klef.ep.models.Faculty;
import com.klef.ep.services.FacultyService;

@WebServlet("/facultyprofileupload")
@MultipartConfig
public class FacultyProfileUpload extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		try {
			Faculty faculty = (Faculty) request.getSession().getAttribute("faculty");
			if (faculty == null) {
				throw new ServletException("Faculty not found in session.");
			}
			
			InitialContext context = new InitialContext();
			FacultyService service = (FacultyService) context.lookup("java:global/EPProject/FacultyServiceImpl!com.klef.ep.services.FacultyService");
			String id = faculty.getId();
			
			Part filePart = request.getPart("file");
			if (filePart == null || filePart.getSubmittedFileName().isEmpty()) {
				throw new ServletException("No file uploaded.");
			}
			
			String filename = filePart.getSubmittedFileName();
			String fileExtension = getFileExtension(filename);
			String newFileName = id + fileExtension;
			
			String currentPath = "D:\\KLU\\2023-2024_SummerTerm\\EP\\PROJECTWORKSPACE\\EPProject\\src\\main\\webapp\\images\\profiles\\faculty";
			String savePath = currentPath + File.separator + newFileName;
			
			try (InputStream fileContent = filePart.getInputStream()) {
				Files.copy(fileContent, Paths.get(savePath), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
			}
			
			service.updateProfile(id, newFileName);
			Faculty updatedFaculty = service.getProfile(faculty.getId());
			request.getSession().setAttribute("faculty", updatedFaculty);
			
			response.sendRedirect("facultyprofile.jsp");
		} catch (Exception e) {
			out.println("Error: " + e.getMessage());
		} finally {
			out.close();
		}
	}

	private String getFileExtension(String fileName) {
	    if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
	        return fileName.substring(fileName.lastIndexOf("."));
	    } else {
	        return "";
	    }
	}
}
