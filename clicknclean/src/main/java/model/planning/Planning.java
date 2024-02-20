package model.planning;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import tools.Db;

public class Planning {

    private ArrayList<CalendarWeek> calendarMonth;
    private ArrayList<TimeSlot> slots;

    // public Planning(int cleanerID) {
    //     this.calendarMonth = createCalendarMonth(cleanerID);
    // }

    // public Planning(ArrayList<CalendarWeek> calendarMonth) {
    // this.calendarMonth = calendarMonth;
    // }

    public Planning(ArrayList<TimeSlot> slots) {
        this.slots = slots;
    }

    // public ArrayList<CalendarWeek> createCalendarMonth(int cleanerID) {
    // this.calendarMonth = new ArrayList<CalendarWeek>();
    // Db connection = new Db();
    // final int START_HR_LIMIT = 8;
    // final int END_HR_LIMIT = 22;

    // for (int i = 0; i < 5; i++) {
    // ArrayList<TimeSlot> days = new ArrayList<TimeSlot>();
    // LocalDate startOfWeek = LocalDate.now();
    // while (startOfWeek.getDayOfMonth() != 1) {
    // startOfWeek = startOfWeek.plusDays(-1);
    // }

    // for (int d = 0; d < 7; d++) {
    // LocalDate date = startOfWeek.plusDays(d + i * 7);
    // for (int h = 0; h < 24; h++) {
    // LocalTime hour = LocalTime.of(h, 0);
    // if (h < START_HR_LIMIT || h >= END_HR_LIMIT) {
    // days.add(new TimeSlot(date, hour, TimeSlot.NOT_AVAILABLE));
    // connection.DAOCreateNewPlanning(date, hour, TimeSlot.NOT_AVAILABLE,
    // cleanerID);
    // } else {
    // days.add(new TimeSlot(date, hour, TimeSlot.AVAILABLE));
    // connection.DAOCreateNewPlanning(date, hour, TimeSlot.AVAILABLE, cleanerID);
    // }
    // }
    // }
    // CalendarWeek calendarWeek = new CalendarWeek(days);
    // this.calendarMonth.add(calendarWeek);
    // }
    // connection.disconnect();
    // connection = null;
    // return calendarMonth;
    // }

    public ArrayList<TimeSlot> getTimeSlots() {
        return this.slots;
    }

    public void setAvailableSlots(LocalDateTime targetLocalDateTime, int newIdMission) {
        for (TimeSlot timeSlotObserved : this.slots) {
            if (timeSlotObserved.getLocalDateTime().equals(targetLocalDateTime)) {
                timeSlotObserved.setIdMission(newIdMission);
            }
        }
    }

    public static void main() {
        Planning plan = new Planning(new ArrayList<>());
        for (TimeSlot slot : plan.slots ) {
            System.out.println(slot.toString());
        }
        System.out.println(plan.slots.size());
    }
}