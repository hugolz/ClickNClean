package model;

import java.lang.ref.Cleaner;
import java.util.ArrayList;
import java.util.List;

public class Mission {
    String propertyAddress;
    String missionDate;
    double propertySurface;
    double duration;
    double cost;
    double commission;
    String ownerId;
    String cleanerId;
    ArrayList<Cleaner> cleanerList;
    String accessCode;
    String keyBoxCode;
    String specialInstructions;
    String startTime;
    String state;

    public Mission( String propertyAddress,
                    String missionDate,
                    double propertySurface,
                    double duration,
                    double cost,
                    double commission,
                    String ownerId,
                    String cleanerId,
                    ArrayList<Cleaner> cleanerList,
                    String accessCode,
                    String keyBoxCode,
                    String specialInstructions,
                    String startTime,
                    String state) {
        this.propertyAddress = propertyAddress;
        this.missionDate = missionDate;
        this.propertySurface = propertySurface;
        this.duration = duration;
        this.cost = cost;
        this.commission = commission;
        this.ownerId = ownerId;
        this.cleanerId = cleanerId;
        this.cleanerList = cleanerList;
        this.accessCode = accessCode;
        this.keyBoxCode = keyBoxCode;
        this.specialInstructions = specialInstructions;
        this.startTime = startTime;
        this.state = state;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public String getMissionDate() {
        return missionDate;
    }

    public void setMissionDate(String missionDate) {
        this.missionDate = missionDate;
    }

    public double getPropertySurface() {
        return propertySurface;
    }

    public double getDuration() {
        return duration;
    }

    public double getCost() {
        return cost;
    }

    public double getCommission() {
        return commission;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getCleanerId() {
        return cleanerId;
    }

    public void setCleanerId(String cleanerId) {
        this.cleanerId = cleanerId;
    }

    public ArrayList<Cleaner> getCleanerList() {
        return cleanerList;
    }

    public void setCleanerList(int ownerLong, int ownerLat, int cleanerLong, int cleanerLat ) {
        this.cleanerList = cleanerList;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getKeyBoxCode() {
        return keyBoxCode;
    }

    public void setKeyBoxCode(String keyBoxCode) {
        this.keyBoxCode = keyBoxCode;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }




}
