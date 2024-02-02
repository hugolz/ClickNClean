import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


/**
 * Creates a TimeSlot using date, hour(time) and availability status
 */
class TimeSlot {
    private LocalDate date;
    private LocalTime hour;
    private int isAvailable;
    public static final int NOT_AVAILABLE = 0;
    public static final int AVAILABLE = 1;
    public static final int ENGAGED_IN_MISSION = 2;

    public TimeSlot(LocalDate date, LocalTime hour, int isAvailable) {
        this.date = date;
        this.hour = hour;
       
        this.isAvailable = isAvailable;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

        /**
     * Gets availability status of a TimeSlot
     * @Status
     * 0 = final NOT_AVAILABLE
     * 1 = final AVAILABLE
     * 2 = final ENGAGED_IN_MISSION
     */
    public int getIsAvailable() {
        return isAvailable;
    }

        /**
     * Sets availability status of a TimeSlot
     * @Status
     * 0 = final NOT_AVAILABLE
     * 1 = final AVAILABLE
     * 2 = final ENGAGED_IN_MISSION
     */
    public void setAvailable(int isAvailable) {
        this.isAvailable = isAvailable;
    }
}

/**
 * Instantiates a planning object using
 * @method createWeeklyTimeSlots()
 */
public class Planning {
    private ArrayList<ArrayList<TimeSlot>> calendarMonth;
    
    public Planning() {
        this.calendarMonth = new ArrayList<ArrayList<TimeSlot>>();
        this.calendarMonth = createCalendarMonth();
    }

    /**
     * Creates an Arraylist of 5 items (5 weeks) --> calendarMonth
     * In each week instantiates all the TimeSlots --> calendarWeek
     * In each TimeSlot, defines the date, the time, and the availability status (true / false)
     */
    public ArrayList<ArrayList<TimeSlot>> createCalendarMonth() {
        final int START_HR_LIMIT = 8;
        final int END_HR_LIMIT = 22;

        for (int i = 0; i < 5; i++) {
            ArrayList<TimeSlot> calendarWeeks = new ArrayList<TimeSlot>();

            LocalDate startOfWeek = LocalDate.now();
            for (int d = 0; d < 7; d++) {
                LocalDate date = startOfWeek.plusDays(d);
                for (int h = 0; h < 24; h++) {
                    LocalTime startOfDay = LocalTime.of(h, 0);
                    if (h < START_HR_LIMIT || h >= END_HR_LIMIT)
                        calendarWeeks.add(new TimeSlot(date, startOfDay, TimeSlot.NOT_AVAILABLE));
                    else
                        calendarWeeks.add(new TimeSlot(date, startOfDay, TimeSlot.AVAILABLE));
                }
            }
            calendarMonth.add(calendarWeeks);
        }

        return calendarMonth;
    }

    /**
     * For each week in the list of weeks, searches each available time slots
     * 
     * @return
     *         An ArrayList of available TimeSlot
     */
    public ArrayList<TimeSlot> getAvailableSlots() {
        ArrayList<TimeSlot> availableList = new ArrayList<TimeSlot>();

        for (ArrayList<TimeSlot> calendarWeeks : calendarMonth) {
            for (TimeSlot timeSlotObserved : calendarWeeks) {
                if (timeSlotObserved.getIsAvailable() == TimeSlot.NOT_AVAILABLE) {
                    availableList.add(timeSlotObserved);
                }
            }
        }
        return availableList;
    }

    /**
     * Sets availability status of a TimeSlot
     * @Status
     * 0 = final NOT_AVAILABLE
     * 1 = final AVAILABLE
     * 2 = final ENGAGED_IN_MISSION
     */
    public void setAvailableSlots(LocalDate targetDate, LocalTime targetTime, int newStatus) {
        for (ArrayList<TimeSlot> calendarWeeks : calendarMonth) {
            for (TimeSlot timeSlotObserved : calendarWeeks) {
                if (timeSlotObserved.getDate().equals(targetDate) && timeSlotObserved.getHour().equals(targetTime)) {
                    timeSlotObserved.setAvailable(newStatus);
                }
            }
        }
    }

    public void getPlanningDB() {
        ArrayList<ArrayList<TimeSlot>> calendarMonth = new ArrayList<ArrayList<TimeSlot>>();
        int count = 0;
        // insert code here when db is born :)
    }

    

}
