package com.klef.ep.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.klef.ep.models.Event;
import com.klef.ep.models.RegisteredEvents;
import com.klef.ep.models.Student;
import com.klef.ep.utils.JavaMailUtil;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class StudentServiceImpl implements StudentService {

	@Override
	public Student checkStudentlogin(String username, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		//System.out.println("2.)"+username+","+password);
		Query qry = em.createQuery("select s from Student s where s.id = ? and s.password = ?");
		//System.out.println(qry);
		qry.setParameter(1, username);
		qry.setParameter(2, password);
		Student student = null;
		//System.out.println(qry.getResultList());
		if(qry.getResultList().size()>0) {
			student = (Student) qry.getSingleResult();
			//System.out.println(Student);
		}
		em.close();
		emf.close();
		return student;
		
	}

	@Override
	public String registerToEvent(String sid, String eid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    String result;
	    System.out.println("Request Recieved");
	    try {
	        em.getTransaction().begin();
	        
	        // Fetch the event and student within the current transaction context
	        Event event = em.find(Event.class, eid);
	        Student student = em.find(Student.class, sid);

	        // Ensure both event and student exist
	        if (event == null) {
	            throw new IllegalArgumentException("Event not found: " + eid);
	        }
	        if (student == null) {
	            throw new IllegalArgumentException("Student not found: " + sid);
	        }

	        // Create and populate the RegisteredEvents entity
	        RegisteredEvents register = new RegisteredEvents();
	        register.setEventid(event.getEid());
	        register.setEventName(event.getEname());
	        register.setFacultyid(event.getFacultyid());
	        register.setFacultyname(event.getFacultyname());
	        register.setStudentid(student.getId());
	        register.setStudentname(student.getName());
	        register.setStatus("NOT_STARTED");
	        register.setWinstatus("NO");

	        em.persist(register);


	        int currentAvailability = event.getAvailability();
	        if (currentAvailability > 0) {
	            event.setAvailability(currentAvailability - 1);
	        } else {
	            throw new IllegalStateException("No availability for event: " + eid);
	        }

	        em.getTransaction().commit();
	        result = "Successfully registered to Event";
	        
	        //JavaMailUtil.sendEventMail(student.getEmail(), event.getEid(), event.getEname(), event.getEdate(), event.getEvenue(), student.getName(), event.getFacultyname());
	        
	    } catch (Exception e) {
	        
	        result = "Error registering to event: " + e.getMessage();
	    } finally {
	        em.close();
	        emf.close();
	    }
	    System.out.println(result);
	    return result;
		
	}

	@Override
	public Student getprofile(String sid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Query qry = em.createQuery("select s from Student s where s.id=?");
		qry.setParameter(1, sid);
		List<Student> students=qry.getResultList();
		em.close();
		emf.close();
		return students.get(0);
	}

	@Override
	public Event getEvent(String eid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Query qry = em.createQuery("select e from Event e where e.id=?");
		qry.setParameter(1, eid);
		List<Event> events=qry.getResultList();
		em.close();
		emf.close();
		return events.get(0);
	}

	@Override
	public List<Event> vieweventhistory(String sid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        List<Event> eventHistory = new ArrayList<>();
        
        try {
            // Fetch registered events by student ID
            Query qry = em.createQuery("SELECT re FROM RegisteredEvents re WHERE re.studentid = ?");
            qry.setParameter(1, sid);
            List<RegisteredEvents> registrations = qry.getResultList();
            
            // Fetch corresponding event details
            for (RegisteredEvents registration : registrations) {
                Event event = em.find(Event.class, registration.getEventid());
                if (event != null) {
                    eventHistory.add(event);
                }
            }
        } finally {
            em.close();
            emf.close();
        }
        
        return eventHistory;
	}

	@Override
	public List<Event> viewevents() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Query qry = em.createQuery("select e from Event e");
		List<Event> events=qry.getResultList();
		em.close();
		emf.close();
		return events;
	}

	@Override
	public int getAvailability(String eid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        Query qry = em.createQuery("select r from RegisteredEvents r where r.eventid=?");
        qry.setParameter(1, qry);
        List <Integer> register = qry.getResultList();
        int registercount = register.get(0);
        
        
        
		return 0;
	}

	@Override
	public boolean isStudentRegisteredForEvent(String studentId, String eventId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    Query qry = em.createQuery("SELECT COUNT(re) FROM RegisteredEvents re WHERE re.studentid = ? AND re.eventid = ?");
	    qry.setParameter(1, studentId);
	    qry.setParameter(2, eventId);
	    long count = (long) qry.getSingleResult();
	    em.close();
	    emf.close();
	    return count > 0;
	}

	@Override
	public String updateProfile(String sid, String file) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    Student student = em.find(Student.class, sid);
	    if (student != null) {
	        student.setProfile(file);
	        em.merge(student);
	        em.getTransaction().commit();
	        em.close();
	        emf.close();
	        return "Profile updated successfully";
	    } else {
	        em.close();
	        emf.close();
	        return "Student not found";
	    }
	}

	@Override
	public Event viewEventByID(String eid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    Event event = em.find(Event.class, eid);
	    em.close();
        emf.close();
		return event;
	}
	
	@Override
	public RegisteredEvents viewregisteredEventbyID(String sid,String eid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    Query qry = em.createQuery("select r from RegisteredEvents r where r.eventid=? and r.studentid=?");
	    qry.setParameter(1, eid);
	    qry.setParameter(2, sid);
	    List<RegisteredEvents> list = qry.getResultList();
	    em.close();
        emf.close();
		return list.get(0);
	}

	@Override
	public String updatepassword(String sid,String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    Student student  = em.find(Student.class, sid);
	    student.setPassword(password);
	    em.getTransaction().commit();
		return "PASSWORD UPDATED SUCCESSFULLY";
	}
	
	@Override
	public int vieweventhistoryCount(String sid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        List<Event> eventHistory = new ArrayList<>();
        
        try {
            // Fetch registered events by student ID
            Query qry = em.createQuery("SELECT re FROM RegisteredEvents re WHERE re.studentid = ?");
            qry.setParameter(1, sid);
            List<RegisteredEvents> registrations = qry.getResultList();
            
            // Fetch corresponding event details
            for (RegisteredEvents registration : registrations) {
                Event event = em.find(Event.class, registration.getEventid());
                if (event != null) {
                    eventHistory.add(event);
                }
            }
        } finally {
            em.close();
            emf.close();
        }
        
        return eventHistory.size();
	}
	

}
