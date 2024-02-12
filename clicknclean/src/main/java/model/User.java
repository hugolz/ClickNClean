package model;

import java.time.LocalDate;

public class User {
	private String email;
	private String pwd;
	private String name;
	private String surName;
	private int phoneN;
	private LocalDate birthDate;
	private boolean suspended;

	public User(String email, String pwd, String name, String surName, int phoneN, LocalDate birthDate, boolean suspended,
			String accountDate) {
		this.email = email;
		this.pwd = pwd;
		this.name = name;
		this.surName = surName;
		this.phoneN = phoneN;
		this.birthDate = birthDate;
		this.suspended = suspended;
		this.accountDate = accountDate;
	}


	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
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

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
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
