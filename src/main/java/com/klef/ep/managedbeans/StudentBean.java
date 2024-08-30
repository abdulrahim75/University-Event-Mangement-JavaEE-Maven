package com.klef.ep.managedbeans;

import java.io.IOException;
import java.util.Random;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.klef.ep.models.Admin;
import com.klef.ep.models.Student;
import com.klef.ep.services.AdminService;
import com.klef.ep.services.StudentService;

@ManagedBean(name = "studentbean",eager = true)
public class StudentBean {
	
	@EJB(lookup = "java:global/EPProject/AdminServiceImpl!com.klef.ep.services.AdminService")
	private AdminService service;
	
	@EJB(lookup = "java:global/EPProject/StudentServiceImpl!com.klef.ep.services.StudentService")
	private StudentService studentservice;

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
	
	@Override
	public String toString() {
		return "StudentBean [service=" + service + ", studentservice=" + studentservice + ", id=" + id + ", name="
				+ name + ", gender=" + gender + ", department=" + department + ", dob=" + dob + ", email=" + email
				+ ", contact=" + contact + ", password=" + password + ", profile=" + profile + "]";
	}
	
	public String addStudent() {
		Student student = new Student();
		Random random = new Random();
        int id = 10000 + random.nextInt(90000); // Generates a number between 10000 and 99999
        student.setId(String.valueOf(id));
        
        student.setName(name);
        student.setGender(gender);
        student.setDepartment(department);
        student.setDob(dob);
        student.setEmail(email);
        student.setContact(contact);
        student.setPassword(dob);
        student.setProfile("nouserpic.jpg");
        //System.out.println(student);
        service.addStudent(student);
        return "viewstudents.jsf";
	}
	
	public void validateStudentLogin() throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		
		Student student = studentservice.checkStudentlogin(id, password);
		
		  if(student!=null){
		     HttpSession session = request.getSession();
		     session.setAttribute("student", student);
		     response.sendRedirect("studenthome.jsp");
		 }
		 else {
		    response.sendRedirect("studentloginfail.jsf");
		 }
	}
	
	public void updateProfile() {
		
	}
	

}
