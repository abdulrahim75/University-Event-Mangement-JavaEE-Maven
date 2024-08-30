package com.klef.ep.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event_table")
public class Event implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "event_id")
	private String eid;
	@Column(name = "event_name",nullable = false,length = 100)
	private String ename;
	@Column(name = "event_department",nullable = false,length = 10)
	private String edepartment;
	@Column(name = "No_Of_Participants",nullable = false)
	private int participants;
	@Column(name = "event_venue",nullable = false,length = 100)
	private String evenue;
	@Column(name = "event_domain",nullable = false)
	private String edomain;
	@Column(name = "event_date",nullable = false)
	private String edate;
	@Column(name = "event_facultyid",nullable = false)
	private String facultyid;
	@Column(name = "event_facultyname",nullable = false)
	private String facultyname;
	@Column(name = "event_availability",nullable = false)
	private int availability;
	@Column(name = "event_starttime",nullable = false)
	private String starttime;
	@Column(name = "event_status",nullable = false)
	private String status;
	
	public int getAvailability() {
		return availability;
	}
	public void setAvailability(int availability) {
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
	@Override
	public String toString() {
		return "Event [eid=" + eid + ", ename=" + ename + ", edepartment=" + edepartment + ", participants="
				+ participants + ", evenue=" + evenue + ", edomain=" + edomain + ", edate=" + edate + ", facultyid="
				+ facultyid + ", facultyname=" + facultyname + ", availability=" + availability + ", starttime="
				+ starttime + ", status=" + status + "]";
	}
	
	

	
	
	
}
