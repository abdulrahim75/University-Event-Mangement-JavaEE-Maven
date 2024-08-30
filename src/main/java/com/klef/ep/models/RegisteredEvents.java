package com.klef.ep.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event_registrations")
public class RegisteredEvents implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sno")
	private long id;
	@Column(name = "student_id",nullable = false)
	private String studentid;
	@Column(name = "student_name",nullable = false)
	private String studentname;
	@Column(name = "faculty_id",nullable = false)
	private String facultyid;
	@Column(name = "faculty_name",nullable = false)
	private String facultyname;
	@Column(name = "event_id",nullable = false)
	private String eventid;
	@Column(name = "event_name",nullable = false)
	private String eventName;
	@Column(name = "event_status",nullable = false)
	private String status;
	@Column(name = "win_status",nullable = false)
	private String winstatus;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getFacultyid() {
		return facultyid;
	}
	public void setFacultyid(String facultyid) {
		this.facultyid = facultyid;
	}
	public String getFacultyname() {
		return facultyname;
	}
	public void setFacultyname(String facultyname) {
		this.facultyname = facultyname;
	}
	public String getEventid() {
		return eventid;
	}
	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getWinstatus() {
		return winstatus;
	}
	public void setWinstatus(String winstatus) {
		this.winstatus = winstatus;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "RegisteredEvents [id=" + id + ", studentid=" + studentid + ", studentname=" + studentname
				+ ", facultyid=" + facultyid + ", facultyname=" + facultyname + ", eventid=" + eventid + ", eventName="
				+ eventName + ", status=" + status + ", winstatus=" + winstatus + "]";
	}
	
	
	
	

}
