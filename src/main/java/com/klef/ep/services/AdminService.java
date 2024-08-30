package com.klef.ep.services;

import java.util.List;

import javax.ejb.Remote;

import com.klef.ep.models.Admin;
import com.klef.ep.models.Event;
import com.klef.ep.models.Faculty;
import com.klef.ep.models.RegisteredEvents;
import com.klef.ep.models.Student;

@Remote
public interface AdminService {
	public String addStudent(Student student);
	public String addFaculty(Faculty faculty);
	public Admin checkadminlogin(String username,String password);
	public List<Student> viewStudents();
	public Student viewStudentbyID(String sid);
	public String deleteStudent(String sid);
	public String updateStudent(Student student);
	public List<Faculty> viewFaculty();
	public Faculty viewFacultybyID(String fid);
	public String deletefaculty(String fid);
	public String updateFaculty(Faculty faculty);
	public String addEvent(Event event);
	public List<Event> viewEvents();
	public Event vieweventbyID(String eid);
	public String updateEvent(Event event);
	public String deleteEvent(String eid);
	public Long getStudentCount();
	public Long getFacultyCount();
	public Long getEventCount();
	public List<RegisteredEvents> viewRegisteredStudents(String eid);
	public String setStatus(String eid,String sid,String status);
	public String cancelEvent(String eid) ;
	public String downloadStudents();
	public String downloadFaculty();
	public String downloadEvents();
	public String announceWinner(String eid,String sid);
	public String getEventWinner(String eid);
	public Long getAttendedCount();
	public Long getNonAttendedCount();
	public Long getregisteredCount(String eid);
	public String downloadRegisteredStudents(String eid);
	public Long getAttendedCountEventwise(String eid);
	public Long getEventCancelled(String eid);
	public String openEvent(String eid);
	public String closeEvent(String eid);
	
	
	
}
