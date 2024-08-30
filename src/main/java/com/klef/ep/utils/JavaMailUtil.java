package com.klef.ep.utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {
	public static void sendStudentMail(String recipient,String sid,String sname) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		String emailString = "shorganizations2863@gmail.com";
		String passwordString = "bpun jeiv otoa ouoi";
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailString, passwordString);
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailString));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("STUDENT DETAILS CONFIRMATION");
			message.setText("Hi " + sname + ",\r\n"
	                + "Welcome to University Event Management Portal\r\n"
	                + "\r\n"
	                + "Your Student ID: " + sid + "\r\n"
	                + "Your Password is your Date of Birth (Make sure to change your password after login)");
			Transport.send(message);
			System.out.println("STUDENT EMAIL SENT SUCCESSFULLY !");
		} catch (Exception e) {
			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE,null,e);
			System.out.println(e.getMessage());
		}
	}
	
	public static void sendFacultyMail(String recipient,String fid,String fname) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		String emailString = "shorganizations2863@gmail.com";
		String passwordString = "bpun jeiv otoa ouoi";
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailString, passwordString);
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailString));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("FACULTY DETAILS CONFIRMATION");
			message.setText("Hi " + fname + ",\r\n"
	                + "Welcome to University Event Management Portal\r\n"
	                + "\r\n"
	                + "Your Student ID: " + fid + "\r\n"
	                + "Your Password is your Date of Birth(DD/MM/YYYY) (Make sure to change your password after login)");
			Transport.send(message);
			System.out.println("FACULTY EMAIL SENT SUCCESSFULLY !");
		} catch (Exception e) {
			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE,null,e);
			System.out.println(e.getMessage());
		}
	}
	
	public static void sendEventMail(String recipient,String eid,String ename,String edate,String evenue,String sname,String fname) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		String emailString = "shorganizations2863@gmail.com";
		String passwordString = "bpun jeiv otoa ouoi";
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailString, passwordString);
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailString));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("EVENT REGISTRATION CONFIRMATION");
			message.setText("Hi " + sname + ",\r\n"
	                + "\r\n"
	                + "You have successfully registered for the event " + ename + " on " + edate + " at the event venue " + evenue + ".\r\n"
	                + "\r\n"
	                + "For more information on the event, contact Event Faculty: " + fname + ".\r\n"
	                + "\r\n"
	                + "Thank you.");

			Transport.send(message);
			System.out.println("EVENT REGISTRATION EMAIL SENT SUCCESSFULLY !");
		} catch (Exception e) {
			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE,null,e);
			System.out.println(e.getMessage());
		}
	}
	
	public static void sendWinnerMail(String recipient,String eid,String ename,String edate,String evenue,String sname) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		String emailString = "shorganizations2863@gmail.com";
		String passwordString = "bpun jeiv otoa ouoi";
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailString, passwordString);
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailString));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("WINNER CERTIFICATE FOR EVENT ID "+eid);
			message.setText("Hi " + sname + ",\r\n"
	                + "\r\n"
	                + "Congratulations on Acheivment for the event " + ename + " conducted on " + edate + " at the event venue " + evenue + ".\r\n"
	                + "\r\n"
	                + "You can download your Acheivment Certificate from your respective student portal. " + ".\r\n"
	                + "\r\n"
	                + "Thank you.");

			Transport.send(message);
			System.out.println("EMAIL SENT SUCCESSFULLY !");
		} catch (Exception e) {
			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE,null,e);
			System.out.println(e.getMessage());
		}
	}
	
	public static void sendNotAttendedtMail(String recipient,String eid,String ename,String edate,String sname) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		String emailString = "shorganizations2863@gmail.com";
		String passwordString = "bpun jeiv otoa ouoi";
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailString, passwordString);
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailString));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("NOT ATTENDED");
			message.setText("Hi " + sname + ",\r\n"
	                + "\r\n"
	                + "You have Not Attended for the event " + ename + " on " + edate + ".\r\n"
	                + "\r\n"
	                + "Thank you.");

			Transport.send(message);
			System.out.println("EMAIL SENT SUCCESSFULLY !");
		} catch (Exception e) {
			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE,null,e);
			System.out.println(e.getMessage());
		}
	}
	
	public static void sendAttendedtMail(String recipient,String eid,String ename,String edate,String sname) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		String emailString = "shorganizations2863@gmail.com";
		String passwordString = "bpun jeiv otoa ouoi";
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailString, passwordString);
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailString));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("ATTENDED");
			message.setText("Hi " + sname + ",\r\n"
	                + "\r\n"
	                + "Thank You for attending the event " + ename + " on " + edate + "you can download your participation certificate from your respective portal.\r\n"
	                + "\r\n"
	                + "Thank you.");

			Transport.send(message);
			System.out.println("EMAIL SENT SUCCESSFULLY !");
		} catch (Exception e) {
			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE,null,e);
			System.out.println(e.getMessage());
		}
	}
	
	public static void sendCancelMail(String recipient,String eid,String ename,String edate,String sname,String fname) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		String emailString = "shorganizations2863@gmail.com";
		String passwordString = "bpun jeiv otoa ouoi";
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailString, passwordString);
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailString));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("EVENT CANCELLED");
			message.setText("Hi " + sname + ",\r\n"
	                + "\r\n"
	                + "We are regret to inform you the event " + ename + "to be conducted on " + edate + "has been cancelled.\r\n"
	                + "\r\n"
	                + "For more information on the event, contact Event Faculty: " + fname + ".\r\n"
	                + "\r\n"
	                + "Thank you.");

			Transport.send(message);
			System.out.println("EMAIL SENT SUCCESSFULLY !");
		} catch (Exception e) {
			Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE,null,e);
			System.out.println(e.getMessage());
		}
	}
	
}
