package model;

import java.util.ArrayList;

public class Owner extends User{
	
	private int ownerID;
	private String serviceType;
	private String ownerReviews;
	private ArrayList<Property> listProperty;

	public Owner(String serviceType, String ownerReviews, int ownerID, String email, String pwd,String name,String surName) {
		super(email, pwd, name, surName);
		this.serviceType = serviceType;
		this.ownerReviews = ownerReviews;
		this.ownerID = ownerID;
	}

	public void createMission() {
		// insert code here
	}

	public void cancelMission() {
		// insert code here
	}

	public void disputeCreate() {
		// insert code here
	}

	public String getMission() {
		return ownerReviews;
	}

	public String getReview() {
		return ownerReviews;
	}

	public void setReview() {
		// insert code here
	}

	public void creatProperty() {
		// insert code here
	}
	
	public void deleteProperty() {
		
	}

	public void addProperty(Property p) {
	// 	this.listProperty;
	// }
}
