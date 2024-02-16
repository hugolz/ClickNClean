package model;

public class Activity {
    private int activityID;
    private String type;
    private boolean opened;	
    private int ownerID;	
    private int cleanerID;	
    private int missionID;	
    private int disputeID;	
    private int adminID;
    
    public Activity(
        int activityID, 
        String type, 
        boolean opened, 
        int ownerID, 
        int cleanerID, 
        int missionID,
        int disputeID, 
        int adminID) {

        this.activityID = activityID;
        this.type = type;
        this.opened = opened;
        this.ownerID = ownerID;
        this.cleanerID = cleanerID;
        this.missionID = missionID;
        this.disputeID = disputeID;
        this.adminID = adminID;
    }

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public int getCleanerID() {
        return cleanerID;
    }

    public void setCleanerID(int cleanerID) {
        this.cleanerID = cleanerID;
    }

    public int getMissionID() {
        return missionID;
    }

    public void setMissionID(int missionID) {
        this.missionID = missionID;
    }

    public int getDisputeID() {
        return disputeID;
    }

    public void setDisputeID(int disputeID) {
        this.disputeID = disputeID;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    

}