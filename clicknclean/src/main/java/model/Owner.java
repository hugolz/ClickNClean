package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Owner extends User {

	private int ownerID;
	private String serviceType;
	private ArrayList<Review>ownerReviews;
	private ArrayList<Property> listProperty;
	private UserStatus status;

//Creates a basic owner
	public Owner(
	    int ownerID,
	    String serviceType,
	    String name,
	    String pwd,
	    String surname,
	    String email,
	    String phoneNumber,
	    LocalDate birthLocalDate,
	    LocalDate accountLocalDate,
	    boolean suspended
	) {
		super(name, pwd, surname, email, phoneNumber, birthLocalDate, accountLocalDate, suspended, UserStatus.OWNER);

		this.serviceType = serviceType;
		this.ownerID = ownerID;
		this.status = UserStatus.OWNER;
	}

// Creates an owner from loaded data
	public Owner(
	    ArrayList<Review> ownerReviews,
	    String serviceType,
	    int ownerID,
	    String name,
	    String pwd,
	    String surname,
	    String email,
	    String phoneNumber,
	    LocalDate birthLocalDate,
	    LocalDate accountLocalDate,
	    boolean suspended,
	    UserStatus status
	) {
		super(name, pwd, surname, email, phoneNumber, birthLocalDate, accountLocalDate, suspended, status);

		this.serviceType = serviceType;
		this.ownerReviews = ownerReviews;
		this.ownerID = ownerID;
		this.status = status;
	}

	public int getOwnerID() {
		return ownerID;
	}

	public String getServiceType() {
		return serviceType;
	}

	public ArrayList<Review> getOwnerReviews() {
		return ownerReviews;
	}

	public ArrayList<Property> getListProperty() {
		return listProperty;
	}

	public UserStatus getStatus() {
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
