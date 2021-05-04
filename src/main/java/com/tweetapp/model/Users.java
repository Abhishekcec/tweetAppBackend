package com.tweetapp.model;

public class Users {

	private String firstName;
	private String lastName;
	private String email;
	private String loginId;
	private String password;
	private String contactNo; 
	public Users(String firstName, String lastName, String email, String loginId, String password, String contactNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.setLoginId(loginId);
		this.password = password;
		this.setContactNo(contactNo);
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	@Override
	public String toString() {
		return "-------------" + firstName + "---------" + email + "---";
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
}

