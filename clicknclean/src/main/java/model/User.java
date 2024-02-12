package model;

import java.util.Date;

public class User {
	private String name;
	private String pwd;
	private String surname;
	private String email;
	private String phoneNumber;
	private Date birthDate;
	private Date accountDate;
	private boolean suspended;

	public User(
	    String name,
	    String pwd,
	    String surname,
	    String email,
	    String phoneNumber,
	    Date birthDate,
	    Date accountDate,
	    boolean suspended
	) {
		this.name = name;
		this.pwd = pwd;
		this.surname = surname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.accountDate = accountDate;
		this.suspended = suspended;
	}


	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getAccountDate() {
		return accountDate;
	}

	public void  setAccountDate(Date accountDate) {
		this.accountDate = accountDate;
	}

	public void connection() {

	}

	public void disconnection() {

	}
}
