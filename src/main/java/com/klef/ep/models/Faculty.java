package com.klef.ep.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "faculty_table")
public class Faculty implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "faculty_id")
	private String id;
	@Column(name = "faculty_name",nullable = false)
	private String name;
	@Column(name = "faculty_gender",nullable = false,length = 10)
	private String gender;
	@Column(name = "faculty_department",nullable = false)
	private String department;
	@Column(name = "faculty_dateofbirth",nullable = false)
	private String dob;
	@Column(name = "faculty_email",nullable = false,unique = true)
	private String email;
	@Column(name = "faculty_contact",nullable = false,unique = true,length = 12)
	private String contact;
	@Column(name = "faculty_password",nullable = false)
	private String password;
	@Column(name = "faculty_profile",nullable = false)
	private String profile;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	
	@Override
	public String toString() {
		return "Faculty [id=" + id + ", name=" + name + ", gender=" + gender + ", department=" + department + ", dob="
				+ dob + ", email=" + email + ", contact=" + contact + ", password=" + password + ", profile=" + profile
				+ "]";
	}
	
}
