package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Owner extends User {

	private int ownerID;
	private String serviceType;
	private ArrayList<Integer> ownerReviews;
	private ArrayList<Integer> listProperty;
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
	    boolean suspended

	) {

		super(name, pwd, surname, email, phoneNumber, birthLocalDate, suspended, UserStatus.OWNER);

		this.serviceType = serviceType;
		this.ownerID = ownerID;
		this.status = UserStatus.OWNER;
	}

// Creates an owner from loaded data
	public Owner(
	    int ownerID,
	    String serviceType,
	    ArrayList<Integer> ownerReviews,
	    ArrayList<Integer> listProperty,
	    String name,
	    String pwd,
	    String surname,
	    String email,
	    String phoneNumber,
	    LocalDate birthLocalDate,
	    boolean suspended
	) {

		super(name, pwd, surname, email, phoneNumber, birthLocalDate, suspended, UserStatus.OWNER);
		this.ownerID = ownerID;
		this.serviceType = serviceType;
		this.ownerReviews = ownerReviews;
		this.listProperty = listProperty;
		this.status = UserStatus.OWNER;
	}

	public int getOwnerID() {
		return ownerID;
	}

	public String getServiceType() {
		return serviceType;
	}

	public ArrayList<Integer> getOwnerReviews() {
		return ownerReviews;
	}

	public ArrayList<Integer> getListProperty() {
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
}
