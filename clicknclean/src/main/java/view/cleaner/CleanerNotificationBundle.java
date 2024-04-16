package view.cleaner;

import model.Activity;
import model.Owner;
import model.Cleaner;
import model.Mission;
import model.Dispute;
import model.Admin;

public class CleanerNotificationBundle {
	private Activity activity;
	private Owner owner;
	private Cleaner cleaner;
	private Mission mission;
	private Dispute dispute;
	private Admin admin;

	public CleanerNotificationBundle(Activity activity, Owner owner, Cleaner cleaner, Mission mission, Dispute dispute, Admin admin) {
		this.activity = activity;
		this.owner = owner;
		this.cleaner = cleaner;
		this.mission = mission;
		this.dispute = dispute;
		this.admin = admin;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Cleaner getCleaner() {
		return cleaner;
	}

	public void setCleaner(Cleaner cleaner) {
		this.cleaner = cleaner;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public Dispute getDispute() {
		return dispute;
	}

	public void setDispute(Dispute dispute) {
		this.dispute = dispute;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}