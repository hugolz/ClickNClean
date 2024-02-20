package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Mission {
    Property property;
    LocalDate missionDate;
    double duration;
    double cost;
    double commission;
    String ownerId;
    String cleanerId;
    ArrayList<Cleaner> cleanerList;
    String startTime;
    MissionStatus state;
    HashMap<Cleaner, LocalDateTime> missionProposals;

    public Mission(
        Property property,
        LocalDate missionDate,
        double duration,
        double cost,
        double commission,
        String ownerId,
        String cleanerId,
        String startTime,
        MissionStatus state,
        HashMap<Cleaner, LocalDateTime> missionProposals
    ) {
        this.property = property;
        this.missionDate = missionDate;
        this.duration = duration;
        this.cost = cost;
        this.commission = commission;
        this.ownerId = ownerId;
        this.cleanerId = cleanerId;
        this.startTime = startTime;
        this.state = state;
        this.missionProposals = missionProposals;

    }

    public LocalDate getMissionDate() {
        return missionDate;
    }

    public void setMissionDate(LocalDate missionDate) {
        this.missionDate = missionDate;
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


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public MissionStatus getState() {
        return state;
    }

    public void setState(MissionStatus state) {
        this.state = state;
    }

    public HashMap<Cleaner, LocalDateTime> getMissionProposals() {
        return missionProposals;
    }

    public void setMissionProposals(HashMap<Cleaner, LocalDateTime> missionProposals) {
        this.missionProposals = missionProposals;
    }

    


}
