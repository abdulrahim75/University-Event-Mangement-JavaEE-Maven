package com.klef.ep.managedbeans;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.klef.ep.models.Admin;
import com.klef.ep.models.Event;
import com.klef.ep.models.Faculty;
import com.klef.ep.models.Student;
import com.klef.ep.services.AdminService;

@ManagedBean(name = "adminbean",eager = true)
public class AdminBean {
	
	@EJB(lookup = "java:global/EPProject/AdminServiceImpl!com.klef.ep.services.AdminService")
	private AdminService service;
	
	
	private String username;
	private String password;
	private boolean loginsuccess;
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isLoginsuccess() {
		return loginsuccess;
	}
	public void setLoginsuccess(boolean loginsuccess) {
		this.loginsuccess = loginsuccess;
	}
	
	
	public void validateAdminLogin() throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		
		Admin admin = service.checkadminlogin(username, password);
		//System.out.println(admin);
		  if(admin!=null){
			 setLoginsuccess(true);
		     HttpSession session = request.getSession();
		     session.setAttribute("admin", admin);
		     response.sendRedirect("adminhome.jsp");
		 }
		 else {
		    response.sendRedirect("fail.jsf");
		 }
	}
	
	public List<Student> getStudentList() {
		List<Student> list = service.viewStudents();
		//System.out.println(list);
		return service.viewStudents();
	}
	public List<Faculty> getFacultyList() {
		return service.viewFaculty();
	}
	public List<Event> getEventList() {
		return service.viewEvents();
	}
	
	public String cancelEvent(String eid) {
		service.cancelEvent(eid);
		return "viewevents.jsf";
	}
	
	public String deleteEvent(String eid) {
		service.deleteEvent(eid);
		return "viewevents.jsf";
	}
	
	public boolean cancelStatus(String eid) {
		Event event = service.vieweventbyID(eid);
		if((event.getStatus()).equals("CANCELLED")) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public String openEventRegistration(String eid) {
		service.openEvent(eid);
		return "viewevents.jsf";
	}
	
	public String closeEventRegistration(String eid) {
		service.closeEvent(eid);
		return "viewevents.jsf";
	}
	
	public boolean openStatus(String eid) {
		Event event = service.vieweventbyID(eid);
		if((event.getStatus()).equals("OPEN")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isCancelled(String eid) {
		Event event = service.vieweventbyID(eid);
		if((event.getStatus()).equals("CANCELLED")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	
}
