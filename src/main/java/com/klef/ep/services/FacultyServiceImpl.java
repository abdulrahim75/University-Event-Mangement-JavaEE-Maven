package com.klef.ep.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.klef.ep.models.Event;
import com.klef.ep.models.Faculty;
import com.klef.ep.models.RegisteredEvents;
import com.klef.ep.models.Student;
import com.klef.ep.utils.JavaMailUtil;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class FacultyServiceImpl implements FacultyService {

	@Override
	public Faculty checkFacultylogin(String username, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		//System.out.println("2.)"+username+","+password);
		Query qry = em.createQuery("select f from Faculty f where f.id = ? and f.password = ?");
		//System.out.println(qry);
		qry.setParameter(1, username);
		qry.setParameter(2, password);
		Faculty faculty = null;
		//System.out.println(qry.getResultList());
		if(qry.getResultList().size()>0) {
			faculty = (Faculty) qry.getSingleResult();
			//System.out.println(faculty);
		}
		em.close();
		emf.close();
		return faculty;
	}

	@Override
	public String registerAnEvent(Event event) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(event);
		em.getTransaction().commit();
		return "Event Added Succesfully";
	}
	@Override
	public Student viewStudentbyID(String sid) {
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
	public Faculty getProfile(String fid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		System.out.println(fid);
//		Faculty faculty = em.find(Faculty.class, fid);
		Query qry = em.createQuery("select f from Faculty f where f.id=?") ;
		qry.setParameter(1, fid);
		List<Faculty> facutltyList = qry.getResultList();
		Faculty f = facutltyList.get(0); 
		System.out.println(f);
		if(f==null) {
			em.close();
			emf.close();
			return null;
		}
		em.close();
		emf.close();
		return f;
	}

	@Override
	public List<Event> viewEventHistory(String fid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Query qry = em.createQuery("select e from Event e where e.facultyid=?");
		qry.setParameter(1, fid);
		List<Event> events=qry.getResultList();
		em.close();
		emf.close();
		return events;
	}
	
	@Override
	public String updateProfile(String fid, String filename) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    Faculty faculty = em.find(Faculty.class, fid);
	    if (faculty != null) {
	    	faculty.setProfile(filename);
	        em.merge(faculty);
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
	public String updatePassword(String fid, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    Faculty faculty  = em.find(Faculty.class, fid);
	    faculty.setPassword(password);
	    em.getTransaction().commit();
		return "PASSWORD UPDATED SUCCESSFULLY";
	}

	@Override
	public Long getAttendedCount(String fid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    Long count = (long) 0;
	    try {
			Query qry = em.createQuery("select count(r) from RegisteredEvents r where r.status=? and r.facultyid=?");
			qry.setParameter(1,"ATTENDED");
			qry.setParameter(2, fid);
			List<Long> countList =qry.getResultList();
			count = countList.get(0);
		} 
	    catch (Exception e) {
			System.out.println(e.getMessage());
		}
	    finally {
	    	em.close();
	        emf.close();
		}
		return count;
	}

	@Override
	public Long getNonAttendedCount(String fid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    Long count = (long) 0;
	    try {
			Query qry = em.createQuery("select count(r) from RegisteredEvents r where r.status=? and r.facultyid=?");
			qry.setParameter(1,"NOT_ATTENDED");
			qry.setParameter(2, fid);
			List<Long> countList =qry.getResultList();
			count = countList.get(0);
		} 
	    catch (Exception e) {
			System.out.println(e.getMessage());
		}
	    finally {
	    	em.close();
	        emf.close();
		}
		return count;
	}

	@Override
	public Long getEventCount(String fid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    Long count = (long) 0;
	    try {
			Query qry = em.createQuery("select count(e) from Event e where e.facultyid=?");
			qry.setParameter(1, fid);
			List<Long> countList =qry.getResultList();
			count = countList.get(0);
		} 
	    catch (Exception e) {
			System.out.println(e.getMessage());
		}
	    finally {
	    	em.close();
	        emf.close();
		}
		return count;
	}

	@Override
	public List<RegisteredEvents> viewRegisteredStudents(String eid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Query qry = em.createQuery("select r from RegisteredEvents r where r.eventid=?");
		qry.setParameter(1, eid);
		List<RegisteredEvents> list = qry.getResultList();
		return list;
	}
	
	@Override
	public Event vieweventbyID(String eid) {
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
	public Long getEventAttendedCount(String eid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    Long count = (long) 0;
	    try {
			Query qry = em.createQuery("select count(r) from RegisteredEvents r where r.status=? and r.eventid=?");
			qry.setParameter(1,"ATTENDED");
			qry.setParameter(2, eid);
			List<Long> countList =qry.getResultList();
			count = countList.get(0);
		} 
	    catch (Exception e) {
			System.out.println(e.getMessage());
		}
	    finally {
	    	em.close();
	        emf.close();
		}
		return count;
	}
	
	@Override
	public String getEventWinner(String eid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    String name = "STILL NOT DECIDED";
	    String win = "YES"; 
	    try {
			Query qry = em.createQuery("select re from RegisteredEvents re where re.eventid=? and re.winstatus=?");
			qry.setParameter(1, eid);
			qry.setParameter(2, win);
			List<RegisteredEvents> events = qry.getResultList();
			RegisteredEvents registeredEvent = events.get(0);
			if(registeredEvent!=null) {
				name = registeredEvent.getStudentname();
			}
			else {
				name = "STILL NOT DECIDED";
			}
			
		}
	    catch (Exception e) {
	    	System.out.println(e.getMessage());
		}
	    finally {
	    	em.close();
	        emf.close();
		}
		return name;
	}

	@Override
	public Long getregisteredCount(String eid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    Long count = (long) 0;
	    try {
			Query qry = em.createQuery("select count(r) from RegisteredEvents r where r.eventid=?");
			qry.setParameter(1, eid);
			List<Long> countList =qry.getResultList();
			count = countList.get(0);
		} 
	    catch (Exception e) {
			System.out.println(e.getMessage());
		}
	    finally {
	    	em.close();
	        emf.close();
		}
		return count;
	}
	@Override
	public String setStatus(String eid,String sid,String status) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager(); 
		em.getTransaction().begin();
		Query qry = em.createQuery("update RegisteredEvents r set r.status=? where r.eventid=? and r.studentid=?");
		qry.setParameter(1, status);
	    qry.setParameter(2, eid);
	    qry.setParameter(3, sid);
	    int n = qry.executeUpdate();
	    if(n>0) {
	    	System.out.println(n+" Records updated Succesfully");
	    	em.getTransaction().commit();
	    	em.close();
	    	emf.close();
	    	if(status.equals("ATTENDED")) {
	    		Event event = vieweventbyID(eid);
	            Student student = viewStudentbyID(sid);
	    		JavaMailUtil.sendAttendedtMail(student.getEmail(), eid, event.getEname(), event.getEdate(), student.getName());
	    	}
	    	else {
	    		Event event = vieweventbyID(eid);
	            Student student = viewStudentbyID(sid);
	    		JavaMailUtil.sendNotAttendedtMail(student.getEmail(), eid, event.getEname(), event.getEdate(), student.getName());
	    	}
	    	return "Status Updated Successfully!";
	    }
	    else {
	    	System.out.println(" No Records updated Succesfully");
	    	em.getTransaction().commit();
	    	em.close();
	    	emf.close();
	    	return "No record Found.";
	    }
		
	}
	@Override
	public String announceWinner(String eid, String sid) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    String status = "Request Not Started";
	    System.out.println(sid+","+eid);
	    try {
	        String winstatus = "YES";
	        System.out.println("Request Received");
	        Query qry = em.createQuery("update RegisteredEvents r set r.winstatus=? where r.eventid=? AND r.studentid=?");
	        qry.setParameter(1, winstatus);
	        qry.setParameter(2, eid);
	        qry.setParameter(3, sid);
	        int n = qry.executeUpdate();
	        if (n > 0) {
	            System.out.println(n + " Record(s) Updated Successfully!");
	            status = "Updated";
	            em.getTransaction().commit();
	        } else {
	            System.out.println("No Event Found!");
	            status = "Not Updated";
	            em.getTransaction().commit();
	        }
	    } catch (Exception e) {
	        System.out.println(e.getMessage()); // Print full stack trace for debugging
	    } finally {
	        em.close();
	        emf.close();
	    }
	    
	    return status;
	}
}
