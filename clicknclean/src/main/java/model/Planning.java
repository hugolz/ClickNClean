package model;
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

    public String toString() {
        return "TimeSlot{" +
                "date=" + date +
                ", hour=" + hour +
                ", isAvailable=" + isAvailable +
                '}';
    }
}

class CalendarWeek {
    private ArrayList<TimeSlot> calendarDay;

    public CalendarWeek(ArrayList<TimeSlot> calendarDay) {
        this.calendarDay = calendarDay;
    }

    public ArrayList<TimeSlot> getCalendarDay() {
        return calendarDay;
    }

}







/**
 * Instantiates a planning object using
 * @method createWeeklyTimeSlots()
 */
public class Planning {
    public ArrayList<CalendarWeek> getCalendarMonth() {
        return calendarMonth;
    }

    private ArrayList<CalendarWeek> calendarMonth;
    
    public Planning() {
        this.calendarMonth = new ArrayList<CalendarWeek>();
        this.calendarMonth = createCalendarMonth();
    }

    /**
     * Creates an Arraylist of 5 items (5 weeks) --> calendarMonth
     * In each week instantiates all the TimeSlots --> calendarWeek
     * In each TimeSlot, defines the date, the time, and the availability status (true / false)
     * Calendar starts at the fist day of the month
     */
    public ArrayList<CalendarWeek> createCalendarMonth() {
        final int START_HR_LIMIT = 8;
        final int END_HR_LIMIT = 22;

        for (int i = 0; i < 5; i++) {
            ArrayList<TimeSlot> days= new ArrayList<TimeSlot>();
            LocalDate startOfWeek = LocalDate.now();
            while (startOfWeek.getDayOfYear() != 1) {
                startOfWeek = startOfWeek.plusDays(-1);
            }

            for (int d = 0; d < 7; d++) {
                LocalDate date = startOfWeek.plusDays(d + i * 7);
                for (int h = 0; h < 24; h++) {
                    LocalTime startOfDay = LocalTime.of(h, 0);
                    if (h < START_HR_LIMIT || h >= END_HR_LIMIT)
                        days.add(new TimeSlot(date, startOfDay, TimeSlot.NOT_AVAILABLE));
                    else
                        days.add(new TimeSlot(date, startOfDay, TimeSlot.AVAILABLE));
                }  
            }
            CalendarWeek calendarWeek = new CalendarWeek(days);
            this.calendarMonth.add(calendarWeek);
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

        for (CalendarWeek calendarWeeks : calendarMonth) {
            for (TimeSlot timeSlotObserved : calendarWeeks.getCalendarDay()) {
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
        for (CalendarWeek calendarWeeks : calendarMonth) {
            for (TimeSlot timeSlotObserved : calendarWeeks.getCalendarDay()) {
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

    public static void main(String[] args) {
        Planning plan = new Planning();
        int count = 0;
        for (int i = 0; i < plan.getAvailableSlots().size(); i++) {
            count++; 
            System.out.println(plan.getAvailableSlots().get(i).toString());
            } 
        System.out.println(count);
        }
    }  