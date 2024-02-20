package model.planning;

import java.time.LocalDateTime;

public class TimeSlot {
    private LocalDateTime datetime;
    private double durationH;
    private int idMission;
    public static final int NOT_AVAILABLE = 0;
    public static final int AVAILABLE = 1;
    public static final int ENGAGED_IN_MISSION = 2;

    public TimeSlot(LocalDateTime datetime, double durationH) {
        this.datetime = datetime;
        this.durationH = durationH;
        this.idMission = -1;
    }

    public TimeSlot(LocalDateTime datetime, double durationH, int idMission) {
        this.datetime = datetime;
        this.durationH = durationH;
        this.idMission = idMission;
    }

    public LocalDateTime getLocalDateTime() {
        return datetime;
    }

    public void setLocalDateTime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public double getDurationH() {
        return this.durationH;
    }

    public void setDurationH(double durationH) {
        this.durationH = durationH;
    }

    public boolean getIsAvailable() {
        return this.idMission == -1;
    }

    public void setIdMission(int idMission) {
        this.idMission = idMission;
    }

    public int getIdMission() {
        return this.idMission;
    }

    public String toString() {
        return "TimeSlot{" +
               "date: " + this.datetime +
               "duration_h: " + this.durationH +
               ", id_mission: " + this.idMission +
               '}';
    }
}