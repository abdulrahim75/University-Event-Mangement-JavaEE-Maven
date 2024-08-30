package com.klef.ep.services;

import java.util.List;

import javax.ejb.Remote;

import java.io.*;
import com.klef.ep.models.Event;
import com.klef.ep.models.RegisteredEvents;
import com.klef.ep.models.Student;

@Remote
public interface StudentService {
	
	public Student checkStudentlogin(String username,String password);
	public String registerToEvent(String sid,String eid);
	public Student getprofile(String sid);
	public Event getEvent(String eid);
	public List<Event> viewevents();
	public List<Event> vieweventhistory(String sid);
	public int getAvailability(String eid);
	public boolean isStudentRegisteredForEvent(String studentId, String eventId);
	public String updateProfile(String sid,String file);
	public Event viewEventByID(String eid);
	public RegisteredEvents viewregisteredEventbyID(String sid,String eid);
	public String updatepassword(String sid,String password);
	int vieweventhistoryCount(String sid);

}
