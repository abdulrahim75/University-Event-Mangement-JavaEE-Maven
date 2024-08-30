package com.klef.ep.services;

import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.klef.ep.models.Admin;
import com.klef.ep.models.Event;
import com.klef.ep.models.Faculty;
import com.klef.ep.models.RegisteredEvents;
import com.klef.ep.models.Student;
import com.klef.ep.utils.JavaMailUtil;



@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class AdminServiceImpl implements AdminService {
	
	
	
	@Override
	public String addStudent(Student student) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(student); 
		em.getTransaction().commit();
		JavaMailUtil.sendStudentMail(student.getEmail(), student.getId(), student.getName());
		em.close();
		emf.close();
		
		return "Student Registered Successfully";
	}

	@Override
	public String addFaculty(Faculty faculty) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(faculty); 
		em.getTransaction().commit();
		JavaMailUtil.sendFacultyMail(faculty.getEmail(), faculty.getId(), faculty.getName());
		em.close();
		emf.close();
		
		return "Faculty Registered Successfully";
	}

	@Override
	public Admin checkadminlogin(String username, String password) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		Query qry = em.createQuery("select a from Admin a where a.username=? and a.password=?");
		
		qry.setParameter(1, username);
		qry.setParameter(2, password);
		Admin admin = null;
		if(qry.getResultList().size()>0) {
			admin = (Admin) qry.getSingleResult();
			
		}
		
		em.close();
		emf.close();
		return admin;
	}

	@Override
	public List<Student> viewStudents() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Query qry = em.createQuery("select s from Student s");
		List<Student> students=qry.getResultList();
		em.close();
		emf.close();
		return students;
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
	public String deleteStudent(String sid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Student student = em.find(Student.class, sid);
		em.remove(student);
		em.getTransaction().commit();
		return "Student Deleted Succesfully!";
	}

	@Override
	public String updateStudent(Student student) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Student s = em.find(Student.class, student.getId());
		s.setName(student.getName());
		s.setEmail(student.getEmail());
		s.setContact(student.getContact());
		s.setDepartment(student.getDepartment());
		s.setDob(student.getDob());
		
		em.getTransaction().commit();
		return "Student Updated Succesfully!";
	}

	@Override
	public List<Faculty> viewFaculty() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Query qry = em.createQuery("select f from Faculty f");
		List<Faculty> faculties=qry.getResultList();
		em.close();
		emf.close();
		return faculties;
	}

	@Override
	public Faculty viewFacultybyID(String fid) {
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
	public String deletefaculty(String fid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Faculty faculty = em.find(Faculty.class, fid);
		em.remove(faculty);
		em.getTransaction().commit();
		return "faculty Deleted Succesfully!";
	}

	@Override
	public String updateFaculty(Faculty faculty) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Faculty s = em.find(Faculty.class, faculty.getId());
		s.setName(faculty.getName());
		s.setEmail(faculty.getEmail());
		s.setContact(faculty.getContact());
		s.setDepartment(faculty.getDepartment());
		s.setDob(faculty.getDob());
		
		em.getTransaction().commit();
		return "Student Updated Succesfully!";
	}

	@Override
	public String addEvent(Event event) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(event);
		em.getTransaction().commit();
		return "Event Added Succesfully";
	}

	@Override
	public List<Event> viewEvents() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Query qry = em.createQuery("select e from Event e");
		List<Event> events=qry.getResultList();
		em.close();
		emf.close();
		return events;
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
	public String deleteEvent(String eid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Event event = em.find(Event.class, eid);
		em.remove(event);
		Query qry = em.createQuery("delete from RegisteredEvents re where re.eventid=?");
		qry.setParameter(1, eid);
		qry.executeUpdate();
		em.getTransaction().commit();
		return "Event Deleted Succesfully!";
	}

	@Override
	public Long getStudentCount() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Query qry = em.createQuery("select count(s) from Student s");
		List<Long> students=qry.getResultList();
		em.close();
		emf.close();
		return students.get(0);
	}

	@Override
	public Long getFacultyCount() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Query qry = em.createQuery("select count(f) from Faculty f");
		List<Long> faculties=qry.getResultList();
		em.close();
		emf.close();
		return faculties.get(0);
	}

	@Override
	public Long getEventCount() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Query qry = em.createQuery("select count(e) from Event e");
		List<Long> events=qry.getResultList();
		em.close();
		emf.close();
		return events.get(0);
	}

	@Override
	public String updateEvent(Event event) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    String result;

	    try {
	        em.getTransaction().begin();

	        // Find the existing event
	        Event existingEvent = em.find(Event.class, event.getEid());
	        if (existingEvent == null) {
	            throw new IllegalArgumentException("Event not found: " + event.getEid());
	        }

	        // Update the event details
	        existingEvent.setEvenue(event.getEvenue());
	        existingEvent.setParticipants(event.getParticipants());
	        existingEvent.setFacultyid(event.getFacultyid());
	        existingEvent.setFacultyname(event.getFacultyname());
	        existingEvent.setEdate(event.getEdate());
	        existingEvent.setStarttime(event.getStarttime());
	        
	        // Calculate the number of registered participants
	        int registeredParticipants = existingEvent.getParticipants() - existingEvent.getAvailability();

	        // Calculate the new availability based on the updated participants
	        int newAvailability = event.getParticipants() - registeredParticipants;
	        if (newAvailability < 0) {
	            throw new IllegalStateException("Number of participants is less than the number of registered participants");
	        }
	        existingEvent.setAvailability(newAvailability);

	        em.getTransaction().commit();
	        result = "Event Updated Successfully";
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        result = "Error updating event: " + e.getMessage();
	    } finally {
	        em.close();
	        emf.close();
	    }

	    return result;
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
	
	public String cancelEvent(String eid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		String status = "CANCELLED"; 
		em.getTransaction().begin();
		Event event = em.find(Event.class, eid);
		event.setStatus("CANCELLED");
		Query qry = em.createQuery("update RegisteredEvents r set r.status=? where r.eventid=?"); 
		qry.setParameter(1, status);
	    qry.setParameter(2, eid);
	    int n = qry.executeUpdate();
	    if(n>0) {
	    	System.out.println(n+" Records updated Succesfully");
	    	em.getTransaction().commit();
	    	em.close();
	    	emf.close();
	    	List<Student> students = viewStudents();
	    	for (Student s:students) {
	    		JavaMailUtil.sendCancelMail(s.getEmail(), eid, event.getEname(), event.getEdate(), s.getName(), event.getFacultyname());
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
	public String downloadStudents() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        
        Query qry = em.createQuery("select s from Student s");
        List<Student> students = qry.getResultList();
        
        StringWriter csvData = new StringWriter();
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        csvData.append("ID,Name,Email,Contact,Department,DOB\n");
        
        for (Student student : students) {
            csvData.append(student.getId()).append(",");
            csvData.append(student.getName()).append(",");
            csvData.append(student.getEmail()).append(",");
            csvData.append(student.getContact()).append(",");
            csvData.append(student.getDepartment()).append(",");
            csvData.append(student.getDob()).append("\n");
        }
        
        em.close();
        emf.close();
        
        return csvData.toString();
	}

	@Override
	public String downloadFaculty() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        
        Query qry = em.createQuery("select f from Faculty f");
        List<Faculty> faculty = qry.getResultList();
        
        StringWriter csvData = new StringWriter();
        csvData.append("ID,Name,Email,Contact,Department,DOB\n");
        
        for (Faculty f : faculty) {
            csvData.append(f.getId()).append(",");
            csvData.append(f.getName()).append(",");
            csvData.append(f.getEmail()).append(",");
            csvData.append(f.getContact()).append(",");
            csvData.append(f.getDepartment()).append(",");
            csvData.append(f.getDob()).append("\n");
        }
        
        em.close();
        emf.close();
        
        return csvData.toString();
	}

	@Override
	public String downloadEvents() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();
        
        Query qry = em.createQuery("select e from Event e");
        List<Event> events = qry.getResultList();
        
        StringWriter csvData = new StringWriter();
        csvData.append("EVENT ID,EVENT NAME,FACULTY ID,FACULTY NAME,EVENT DEPARTMENT,EVENT DATE,EVENT VENUE,EVENT CAPACITY,EVENT ATTENED COUNT,EVENT REGISTERED COUNT,EVENT WINNER\n");
        
        for (Event e:events) {
            csvData.append(e.getEid()).append(",");
            csvData.append(e.getEname()).append(",");
            csvData.append(e.getFacultyid()).append(",");
            csvData.append(e.getFacultyname()).append(",");
            csvData.append(e.getEdepartment()).append(",");
            csvData.append(e.getEdate()).append(",");
            csvData.append(e.getEvenue()).append(",");
            csvData.append(Integer.toString(e.getParticipants())).append(",");
            csvData.append(Long.toString(getAttendedCountEventwise(e.getEid()))).append(",");
            csvData.append(Long.toString(getregisteredCount(e.getEid()))).append(",");
            csvData.append(getEventWinner(e.getEid())).append("\n");
        }
        
        em.close();
        emf.close();
        
        return csvData.toString();
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
	            Event event = vieweventbyID(eid);
	            Student student = viewStudentbyID(sid);
	            JavaMailUtil.sendWinnerMail(student.getEmail(), eid, event.getEname(), event.getEdate(), event.getEvenue(), student.getName());
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
	public Long getAttendedCount() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    Long count = (long) 0;
	    try {
			Query qry = em.createQuery("select count(r) from RegisteredEvents r where r.status=?");
			qry.setParameter(1,"ATTENDED");
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
	public Long getAttendedCountEventwise(String eid) {
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
	public Long getNonAttendedCount() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	    EntityManager em = emf.createEntityManager();
	    Long count = (long) 0;
	    try {
			Query qry = em.createQuery("select count(r) from RegisteredEvents r where r.status=?");
			qry.setParameter(1,"NOT_ATTENDED");
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
	public String downloadRegisteredStudents(String eid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		StringWriter csvData = new StringWriter();
		System.out.println("Request Recieved for Event ID "+eid);
		try {
			Query qry = em.createQuery("select r from RegisteredEvents r where r.eventid=?");
			qry.setParameter(1, eid);
			List<RegisteredEvents> list = qry.getResultList();
			
	        csvData.append("EVENT ID,EVENT NAME,STUDENT ID,STUDENT NAME,STUDENT DEPARTMENT,STUDENT MAIL,EVENT STATUS\n");
	        
	        if(list==null||list.isEmpty()) {
	        	csvData.append("No data is fetched.");
	        }
	        
	        for (RegisteredEvents re : list) {
	        	//System.out.println(re.getId());
	            csvData.append(re.getEventid()).append(",");
	            csvData.append(re.getEventName()).append(",");
	            csvData.append(re.getStudentid()).append(",");
	            csvData.append(re.getStudentname()).append(",");
	            Query qry2 = em.createQuery("select s from Student s where s.id=?");
	            qry2.setParameter(1, re.getStudentid());
	            List<Student> tempList = qry2.getResultList();
	            Student student = tempList.get(0);
	            csvData.append(student.getDepartment()).append(",");
	            csvData.append(student.getEmail()).append(",");
	            csvData.append(re.getStatus()).append("\n");
	        }        
		}
		catch (Exception e) {
			System.out.println("Error : "+e.getMessage());
		}
		finally {
			em.close();
	        emf.close();
		}
		return csvData.toString();
	}
	
	@Override
	public Long getEventCancelled(String eid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		Long count = (long)0;
		try {
			Query qry = em.createQuery("select count(re) from RegisteredEvents re where re.status=? and re.eventid=?");
			
			qry.setParameter(1, "CANCELLED");
			qry.setParameter(2, eid);
			
			List<Long> countlist = qry.getResultList();
			count = countlist.get(0);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		em.close();
		emf.close();
		return count;
	}
	
	@Override
	public String openEvent(String eid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Event event = em.find(Event.class, eid);
		event.setStatus("OPEN");
		em.getTransaction().commit();
		em.close();
		emf.close();
		return "EVENT OPENED SUCCESSFULLY";
	}
	
	@Override
	public String closeEvent(String eid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Event event = em.find(Event.class, eid);
		event.setStatus("CLOSE");
		em.getTransaction().commit();
		em.close();
		emf.close();
		return "EVENT OPENED SUCCESSFULLY";
	}
	
	
		
}
