package model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Mission {
    Property property;
    LocalDateTime missionDate;
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
        LocalDateTime missionDate,
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
        this.duration = setDuration(property.getPropertySurface());
        this.cost = cost;
        this.commission = commission;
        this.ownerId = ownerId;
        this.cleanerId = cleanerId;
        this.startTime = startTime;
        this.state = state;
        this.missionProposals = missionProposals;

    }

    public LocalDateTime getMissionDate() {
        return missionDate;
    }

    public void setMissionDate(LocalDateTime missionDate) {
        this.missionDate = missionDate;
    }

    public double getDuration() {
        return duration;
    }




/**
 * Changes duration using mission's property surface 
 *  → < 30m2 → 1h
    → 30-40m2 → 2h
    → 40-60m2 → 2h30
    → 60-80m2 → 3h
    → 80-100m2 → 3h30
    → >100m2 → 4h
 * @param surface
 */
    public static double setDuration(int surface) {
        double duration = 0;
        if (surface <= 30) duration = 1.0;
        else if (surface > 30 && surface <= 40) duration = 2.;
        else if (surface > 40 && surface <= 60) duration = 2.5;
        else if (surface > 60 && surface <= 80) duration = 3.;
        else if (surface > 80 && surface <= 100) duration = 3.5;
        else if (surface > 100) duration = 4.;

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

    public void addMissionProposals(Cleaner cleaner, LocalDateTime startingHour) {
        this.missionProposals.put(cleaner, startingHour);
    }

    


}
