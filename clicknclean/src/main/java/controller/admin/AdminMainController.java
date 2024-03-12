package controller.admin;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.ScrollPane;
import view.Window;
import model.Activity;
import model.ActivityType;
import model.Admin;
import model.Cleaner;
import tools.Db;
import view.admin.AdminMain;

public class AdminMainController {

        public AdminMainController(Window window, Admin admin, int type, int id, int status) throws SQLException, Exception {
            
            switch(type) {
                case 1 : {
                    //Cleaner to confirm / block
                    if (status == 1) {
                        Db connection = new Db();
                        connection.DAOConfirmCleaner(id);
                        connection.DAOaddActivity(ActivityType.CLEANER_ACCOUNT_CONFIRMED, id, null, id, null, null, 1);
                        connection.disconnect();
                        connection = null;
                    }

                    if (status == 99) {
                        Db connection = new Db();
                        connection.DAOConfirmCleaner(id);
                        connection.DAOaddActivity(ActivityType.CLEANER_ACCOUNT_NOT_CONFIRMED, id, null, id, null, null, 1);
                        connection.disconnect();
                        connection = null;
                    }
                
                // TODO : case 2 : 

                }
            }


            ArrayList<Activity> activitiesList = refreshActivities(admin.getAdminId());
            ArrayList<Cleaner> cleanersToConfirm = new ArrayList<>();



            for (Activity each : activitiesList) {
                if (each.getType() == ActivityType.CLEANER_WAITING_TO_BE_CONFIRMED) {
                    Db connection = new Db();
                    Cleaner currentCleaner = connection.DAOReadCleaner(each.getCleanerId());
                    if (currentCleaner.isConfirmedId() != true && currentCleaner.isSuspended() != true) cleanersToConfirm.add(currentCleaner);
                    
                }
            }
            window.setScene(new AdminMain(new ScrollPane(), window, admin, cleanersToConfirm));
        }

        public ArrayList<Activity> refreshActivities(int adminId) throws SQLException, Exception {
        Db connection = new Db();
        ArrayList<Activity> refActivities = connection.DAOReadActivities(adminId);
        connection.disconnect();
        connection = null;
        
        return refActivities;
        }
}