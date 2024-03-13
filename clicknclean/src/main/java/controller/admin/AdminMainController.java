package controller.admin;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.ScrollPane;
import javafx.util.Pair;
import view.Window;
import model.Activity;
import model.ActivityType;
import model.Admin;
import model.Cleaner;
import model.Dispute;
import model.DisputeType;
import model.Mission;
import model.MissionStatus;
import tools.Db;
import view.admin.AdminMain;

public class AdminMainController {

        public AdminMainController(
            Window window, 
            Admin admin, 
            int type, 
            int disputeId, 
            int cleanerId, 
            int ownerId, 
            int status, 
            String decision) throws SQLException, Exception {
            
            switch(type) {
                case 1 : {
                    //Cleaner to confirm / block
                    if (status == 1) {
                        Db connection = new Db();
                        connection.DAOConfirmCleaner(cleanerId);
                        connection.DAOaddActivity(ActivityType.CLEANER_ACCOUNT_CONFIRMED, cleanerId, null, cleanerId, null, null, 1);
                        connection.disconnect();
                    }

                    else  {
                        Db connection = new Db();
                        connection.DAOSuspendUser(cleanerId, true);
                        connection.disconnect();
                    }
                    break;
                }
                case 2 : {
                    //Cleaner to confirm / block
                    if (status == 1) {
                        Db connection = new Db();
                        connection.DAOResolveDispute(disputeId, MissionStatus.RESOLVED_DISPUTE_CLEANER_IS_RIGHT, decision);
                        connection.DAOaddActivity(ActivityType.DISPUTE_RESOLVED, cleanerId, ownerId, cleanerId, null, disputeId, 1);
                        connection.DAOaddActivity(ActivityType.DISPUTE_RESOLVED, ownerId, ownerId, cleanerId, null, disputeId, 1);
                        connection.disconnect();
                    }

                    else  {
                        Db connection = new Db();
                        connection.DAOResolveDispute(disputeId, MissionStatus.RESOLVED_DISPUTE_OWNER_IS_RIGHT, decision);
                        connection.DAOaddActivity(ActivityType.DISPUTE_RESOLVED, ownerId, ownerId, cleanerId, null, disputeId, 1);
                        connection.DAOaddActivity(ActivityType.DISPUTE_RESOLVED, cleanerId, ownerId, cleanerId, null, disputeId, 1);
                        connection.disconnect();
                    }
                    break;
                }


            }



            ArrayList<Activity> activitiesList = refreshActivities(admin.getAdminId());
            ArrayList<Cleaner> cleanersToConfirm = new ArrayList<>();
            ArrayList<Dispute> disputesToSolve = new ArrayList<>();
            ArrayList<Pair<Dispute, Mission>> disputeList = new ArrayList<>();
            
            for (Activity each : activitiesList) {

/*-----------------------------------------Displaying Cleaners waiting to be confirmed-------------------------- */                
                if (each.getType() == ActivityType.CLEANER_WAITING_TO_BE_CONFIRMED) {
                    Db connection = new Db();
                    Cleaner currentCleaner = connection.DAOReadCleaner(each.getCleanerId());

                    if (currentCleaner.isConfirmedId() == false && currentCleaner.isSuspended() == false) {
                        cleanersToConfirm.add(currentCleaner);
                        //System.out.println("entered in activity " + activitiesList.get(0).getActivityId() + "cleaner is : " + currentCleaner.getCleanerId());
                    }
                    
                }
/*-----------------------------------------Displaying Disputes to be solved-------------------------- */
                if (each.getType() == ActivityType.DISPUTE_OPENED) {
                    Db connection = new Db();
                    
                    Dispute currentDispute = connection.DAOReadDispute(each.getDisputeId());
                    Mission currentMission = connection.DAOReadMission(each.getMissionId());
                    if (currentDispute.getType() == DisputeType.NO_TYPE) {
                        System.out.println("entered in dispute : " + currentDispute);
                        disputeList.add(new Pair<>(currentDispute, currentMission));
                    }
                    
                }
            }



            window.setScene(new AdminMain(new ScrollPane(), window, admin, cleanersToConfirm, disputeList));
        }

        public ArrayList<Activity> refreshActivities(int adminId) throws SQLException, Exception {
        Db connection = new Db();
        ArrayList<Activity> refActivities = connection.DAOReadActivities(adminId);
        connection.disconnect();
        connection = null;
        
        return refActivities;
        }
}