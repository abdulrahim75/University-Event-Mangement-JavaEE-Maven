package com.klef.ep.services;

import java.util.List;

import javax.ejb.Remote;

import com.klef.ep.models.Event;
import com.klef.ep.models.Faculty;
import com.klef.ep.models.RegisteredEvents;
import com.klef.ep.models.Student;

@Remote
public interface FacultyService {
	public Faculty checkFacultylogin(String username,String password);
	public String registerAnEvent(Event event);
	public Faculty getProfile(String fid);
	public List<Event> viewEventHistory(String fid);
	public String updateProfile(String fid,String filename);
	public String updatePassword(String fid,String password);
	public Long getAttendedCount(String fid);
	public Long getNonAttendedCount(String fid);
	public Long getEventCount(String fid);
	public List<RegisteredEvents> viewRegisteredStudents(String eid);
	public Event vieweventbyID(String eid);
	public Long getEventAttendedCount(String eid);
	public String getEventWinner(String eid);
	public Long getregisteredCount(String eid);
	public String setStatus(String eid,String sid,String status);
	public String announceWinner(String eid, String sid);
	public Student viewStudentbyID(String sid);
}
