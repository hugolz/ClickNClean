package model;

/**
 * It is the parent class of Administrator, Owner and Cleaner. It defines the
 * characteristics of a user
 */

public class User {
	private String email;
	private String pwd;
	private String name;
	private String surName;
	private int phoneN;
	private String birthDate;

	public User(String email, String pwd, String name, String surName) {
		this.email = email;
		this.pwd = pwd;
		this.name = name;
		this.surName = surName;
	}

	private String accountDate;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public int getPhoneN() {
		return phoneN;
	}

	public void setPhoneN(int phoneN) {
		this.phoneN = phoneN;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getAccountDate() {
		return accountDate;
	}

	public void connection() {

	}

	public void disconnection() {

	}
}
