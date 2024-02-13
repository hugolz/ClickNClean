package model;
import java.util.ArrayList;
import java.time.LocalDate;



public class Admin extends User {

    public Admin(String name, String pwd, String surname, String email, String phoneNumber, LocalDate birthLocalDate, LocalDate accountLocalDate, boolean suspended) {
        super(name, pwd, surname, email, phoneNumber, birthLocalDate, accountLocalDate, suspended);
    }

//-------------------------------------------------------------------------------------------------------------------------
// Manage users

    /**
     * @Method
     *         Confirms cleaner profile whether ID and photo matches, motivations are
     *         ok
     */
    public void cleanerValidate() {
        // Insert code here
    }

    /**
     * @Method Suspends a Cleaner's access to all features
     */
    public void suspendUser(int userID) {
        // Insert code here
    }


    public void restoreUser(int cleanerID) {
        // Insert code here
    }

    public void cancelMission(int missionID) {
        // Insert code here
    }


    public void resoveDispute(int missionID) {
        // Insert code here
    }


//-------------------------------------------------------------------------------------------------------------------------
// Searching features

    // /**
    //  * Calls from SQL requests API to search a profile with a given Cleaner ID
    //  * @param cleanerID
    //  * @return Cleaner object
    //  */
    // public Cleaner getCleaner(int cleanerID) {
    //     Cleaner cleanerRes = request(cleanerID);
    //     return cleanerRes;
    // }

    // /**
    //  * Calls from SQL requests API to search a profile with a given Owner ID
    //  * @param ownerID
    //  * @return Owner object
    //  */
    // public Owner getOwner(int ownerID) {
    //     Owner ownerRes = request(ownerID);
    //     return ownerRes;
    // }

    // /**
    //  * Uses duration (day / week / month) to execute request from SQL API, return total missions number
    //  * @return amount
    //  */
    // public int missionCount(String duration, String missions) {
    //     int amount = requestCount(duration, "missions");
    //     return amount;
    // }

    // /**
    //  * Uses duration (day / week / month) to execute request from SQL API, return total sales revenue
    //  * @return amount
    //  */
    // public int totalAmount(String duration, String revenue) {
    //     int amount = requestCount(duration, "revenue");
    //     return amount;
    // }


    // /**
    //  * Uses duration to return total commission given a period
    //  * @param duration
    //  * @param netRevenue
    //  * @return
    //  */
    // public int netRevenue(String duration, String netRevenue) {
    //     int amount = requestCount(duration, "netRevenue");
    //     return amount;
    // }

    // /**
    //  * Gathers in an ArrayList all the missions on a given period
    //  * @param startLocalDate
    //  * @param endLocalDate
    //  * @return ArrayList
    //  */
    // public ArrayList<Mission> searchLocalDate(String startLocalDate, String endLocalDate) {
    //     // Insert code here (SQL API needed)
    //     ArrayList<Mission> result = new ArrayList<Mission>();
    //     return result;
    // }


    public ArrayList<String> getSatisfactions(String userType) {
        // Insert code here
        ArrayList<String> satisfactions = new ArrayList<String>();
        return satisfactions;
    }

    public ArrayList<String> getCleanerMotivation(String userType) {
        // Insert code here
        ArrayList<String> motivation = new ArrayList<String>();
        return motivation;
    }

}
