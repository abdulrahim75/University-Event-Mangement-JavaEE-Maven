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

import com.klef.ep.models.Student;
import com.klef.ep.services.StudentService;

@WebServlet("/studentprofileupload")
@MultipartConfig
public class StudentProfileUpload extends HttpServlet  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void service(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException {
		PrintWriter out = response.getWriter();
		try {
			Student student = (Student)request.getSession().getAttribute("student");
			InitialContext context = new InitialContext();
			StudentService service=(StudentService)context.lookup("java:global/EPProject/StudentServiceImpl!com.klef.ep.services.StudentService");
			String id = student.getId();
			//out.print(id+"<br>");
			Part filePart = request.getPart("file");
		    String filename = filePart.getSubmittedFileName();
		    String fileExtension = getFileExtension(filename);
		    String newFileName = id + fileExtension;
		    String currentPath = "D:\\KLU\\2023-2024_SummerTerm\\EP\\PROJECTWORKSPACE\\EPProject\\src\\main\\webapp\\images\\profiles\\student";
		    String savePath = currentPath + File.separator;
		    //out.println(savePath+newFileName);
		    InputStream fileContent = filePart.getInputStream();
		    Files.copy(fileContent, Paths.get(savePath, newFileName), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
		    service.updateProfile(id, newFileName);
		    Student updatedStudent = service.getprofile(student.getId());
		    request.getSession().setAttribute("student", updatedStudent);
		    response.sendRedirect("studentprofile.jsp");
		}
		catch (Exception e) {
			out.println(e.getMessage());
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
