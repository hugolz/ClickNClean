package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Owner extends User {

	private int ownerID;
	private String serviceType;
	private String ownerReviews;
	private ArrayList<Property> listProperty;
	private int status;

	public Owner(
	    String serviceType,
	    String ownerReviews,
	    int ownerID,
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
		super(name, pwd, surname, email, phoneNumber, birthLocalDate, accountLocalDate, suspended, status);

		this.serviceType = serviceType;
		this.ownerReviews = ownerReviews;
		this.ownerID = ownerID;
		this.status = 3;
	}


	public int getOwnerID() {
		return ownerID;
	}



	public String getServiceType() {
		return serviceType;
	}



	public String getOwnerReviews() {
		return ownerReviews;
	}



	public ArrayList<Property> getListProperty() {
		return listProperty;
	}



	public int getStatus() {
		return status;
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

	public void addProperty(Property property) {
		this.listProperty.add(property);
	}
}
