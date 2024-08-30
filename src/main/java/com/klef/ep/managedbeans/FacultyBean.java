package com.klef.ep.managedbeans;

import java.io.IOException;
import java.util.Random;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.klef.ep.models.Admin;
import com.klef.ep.models.Faculty;
import com.klef.ep.services.AdminService;
import com.klef.ep.services.FacultyService;

@ManagedBean(name = "facultybean",eager = true)
public class FacultyBean {
	
	@EJB(lookup = "java:global/EPProject/AdminServiceImpl!com.klef.ep.services.AdminService")
	private AdminService service;
	
	@EJB(lookup = "java:global/EPProject/FacultyServiceImpl!com.klef.ep.services.FacultyService")
	private FacultyService facultyService;

	private String id;
	private String name;
	private String gender;
	private String department;
	private String dob;
	private String email;
	private String contact;
	private String password;
	private String profile;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String addFaculty() {
		Faculty faculty = new Faculty();
		Random random = new Random();
		int id = 1000 + random.nextInt(9000); // Generates a number between 1000 and 9999
		faculty.setId(String.valueOf(id));
		faculty.setName(name);
		faculty.setGender(gender);
		faculty.setDepartment(department);
		faculty.setDob(dob);
		faculty.setEmail(email);
		faculty.setContact(contact);
		faculty.setPassword(dob);
		faculty.setProfile("nouserpic.jpg");
		//System.out.println(faculty);
		service.addFaculty(faculty);
		return "viewfaculty.jsf";
	}
	
	public void validateFacultyLogin() throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		
		Faculty faculty = facultyService.checkFacultylogin(id, password);
		System.out.println("1.)"+id+","+password);
		  if(faculty!=null){
		     HttpSession session = request.getSession();
		     session.setAttribute("faculty", faculty);
		     response.sendRedirect("facultyhome.jsp");
		 }
		 else {
		    response.sendRedirect("facultyloginfail.jsf");
		 }
	}
}
