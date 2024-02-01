import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


/**
 * Creates a TimeSlot using date, hour(time) and availability status
 */
class TimeSlot {
    private LocalDate date;
    private LocalTime hour;
    private boolean isAvailable;

    public TimeSlot(LocalDate date, LocalTime hour, boolean isAvailable) {
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}

/**
 * Instantiates a planning object using
 * @method createWeeklyTimeSlots()
 */
public class Planning {
    private ArrayList<ArrayList<TimeSlot>> weeklySlotsList;
    
    public Planning() {
        this.weeklySlotsList = createWeeklyTimeSlots();
    }

    /**
     * Creates an Arraylist of 5 items (5 weeks) --> weeklyTimeSlot
     * In each week instantiates all the TimeSlots --> weekTimeSlot
     * In each TimeSlot, defines the date, the time, and the availability status (true / false)
     */
    public ArrayList<ArrayList<TimeSlot>> createWeeklyTimeSlots() {
        ArrayList<TimeSlot> weekTimeSlots;
        final int START_HR_LIMIT = 8;
        final int END_HR_LIMIT = 22;

        for (int i = 0; i < 5; i++) {
            weekTimeSlots = new ArrayList<TimeSlot>();

            LocalDate startOfWeek = LocalDate.now();
            for (int d = 0; d < 7; d++) {
                LocalDate date = startOfWeek.plusDays(d);
                for (int h = 0; h < 24; h++) {
                    LocalTime startOfDay = LocalTime.of(h, 0);
                    if (h < START_HR_LIMIT || h >= END_HR_LIMIT)
                        weekTimeSlots.add(new TimeSlot(date, startOfDay, false));
                    else
                        weekTimeSlots.add(new TimeSlot(date, startOfDay, true));
                }
            }
            this.weeklySlotsList.add(weekTimeSlots);
        }

        return weeklySlotsList;
    }

    /**
     * For each week in the list of weeks, searches each available time slots
     * 
     * @return
     *         An ArrayList of available TimeSlot
     */
    public ArrayList<TimeSlot> getAvailableSlots() {
        ArrayList<TimeSlot> availableList = new ArrayList<TimeSlot>();

        for (ArrayList<TimeSlot> weekTimeSlots : weeklySlotsList) {
            for (TimeSlot timeSlotObserved : weekTimeSlots) {
                if (timeSlotObserved.isAvailable()) {
                    availableList.add(timeSlotObserved);
                }
            }

        }

        return availableList;
    }

    /**
     * Sets availability status of a TimeSlot
     *
     */
    public void setAvailableSlots(LocalDate targetDate, LocalTime targetTime, boolean newStatus) {
        for (ArrayList<TimeSlot> weekTimeSlots : weeklySlotsList) {
            for (TimeSlot timeSlotObserved : weekTimeSlots) {
                if (timeSlotObserved.getDate().equals(targetDate) && timeSlotObserved.getHour().equals(targetTime)) {
                    timeSlotObserved.setAvailable(newStatus);
                }
            }

        }
    }


}
