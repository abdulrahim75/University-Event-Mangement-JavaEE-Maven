package com.klef.ep.managedbeans;

import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.klef.ep.models.Event;
import com.klef.ep.models.Faculty;
import com.klef.ep.services.AdminService;

@ManagedBean(name = "eventbean",eager = true)
public class EventBean {
	@EJB(lookup = "java:global/EPProject/AdminServiceImpl!com.klef.ep.services.AdminService")
	private AdminService service;
	
	private String eid;
	private String ename;
	private String edepartment;
	private int participants;
	private String evenue;
	private String edate;
	private String facultyid;
	private String facultyname;
	private String edomain;
	private String availability;
	private String starttime;
	private String status;
	
	
	
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String getFacultyname() {
		return facultyname;
	}
	public void setFacultyname(String facultyname) {
		this.facultyname = facultyname;
	}
	public String getFacultyid() {
		return facultyid;
	}
	public void setFacultyid(String facultyid) {
		this.facultyid = facultyid;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEdepartment() {
		return edepartment;
	}
	public void setEdepartment(String edepartment) {
		this.edepartment = edepartment;
	}
	public int getParticipants() {
		return participants;
	}
	public void setParticipants(int participants) {
		this.participants = participants;
	}
	public String getEvenue() {
		return evenue;
	}
	public void setEvenue(String evenue) {
		this.evenue = evenue;
	}
	public String getEdate() {
		return edate;
	}
	public void setEdate(String edate) {
		this.edate = edate;
	}
	
	public String getedomain() {
		return edomain;
	}
	public void setedomain(String edomain) {
		this.edomain = edomain;
	}

	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	public String addEvent() {
		Random random = new Random();
		Event event = new Event();
		Faculty faculty=service.viewFacultybyID(facultyid);
		System.out.println(facultyid);
		if(faculty==null) {
			return "fail.jsf";
		}
		int id = 100000 + random.nextInt(900000);
		event.setEid(Integer.toString(id));
		event.setEname(ename);
		event.setEdepartment(edepartment);
		event.setEvenue(evenue);
		event.setFacultyid(facultyid);
		event.setFacultyname(faculty.getName());
		event.setParticipants(participants);
		event.setEdate(edate);
		event.setedomain(edomain);
		event.setAvailability(participants);
		event.setStarttime(starttime);
		event.setStatus("OPEN");
		//System.out.println(event);
		service.addEvent(event);
		return "viewevents.jsf";
	}
	
	
	public List<Event> getEventList(){
		return service.viewEvents();
	}
	
}
