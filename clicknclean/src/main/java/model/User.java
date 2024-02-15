package model;

import java.time.LocalDate;

public class User {
	private String name;
	private String pwd;
	private String surname;
	private String email;
	private String phoneNumber;
	private LocalDate birthLocalDate;
	private LocalDate accountLocalDate;
	private boolean suspended;
	private int status; 
	/*
	 * Status index : 
	 * Admin --> 0
	 * Owner --> 1
	 * Cleaner --> 2
	 */

	//Basic user  constructor
	public User(
	    String name,
	    String pwd,
	    String surname,
	    String email,
	    String phoneNumber,
	    LocalDate birthLocalDate,
	    LocalDate accountLocalDate,
	    boolean suspended,
		int status
	) {
		this.name = name;
		this.pwd = pwd;
		this.surname = surname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.birthLocalDate = birthLocalDate;
		this.accountLocalDate = accountLocalDate;
		this.suspended = suspended;
		this.status = status;
	}


	//Admin-friendly constructor
	public User(
	    String name,
	    String pwd,
	    String surname,
	    String email,
		int status
	) {
		this.name = name;
		this.pwd = pwd;
		this.surname = surname;
		this.email = email;
		this.status = status;
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

	public LocalDate getBirthLocalDate() {
		return this.birthLocalDate;
	}

	public void setBirthLocalDate(LocalDate birthLocalDate) {
		this.birthLocalDate = birthLocalDate;
	}

	public LocalDate getAccountLocalDate() {
		return accountLocalDate;
	}

	public void  setAccountLocalDate(LocalDate accountLocalDate) {
		this.accountLocalDate = accountLocalDate;
	}

	public int getStatus() {
		return this.status;
	}

	public void connection() {

	}

	public void disconnection() {

	}
}