package controller.cleaner;

import java.util.ArrayList;

import javafx.scene.layout.VBox;
import tools.Db;
import view.Window;
import view.cleaner.CleanerNotification;
import view.cleaner.CleanerNotificationBundle;

import model.Activity;
import model.Owner;
import model.Cleaner;
import model.Mission;
import model.Dispute;
import model.Admin;

public class CleanerNotificationController {
    public CleanerNotificationController(Window window, Cleaner cleaner) {
        Db db_conn = new Db();

        ArrayList<Activity> activities = new ArrayList<Activity>();

        try {
            activities = db_conn.DAOReadActivities(cleaner.getCleanerId());
        } catch (Exception e) {
            System.err.println("Could not fetch activies for cleaner " + cleaner.getCleanerId() + " due to: " + e);
        }

        ArrayList<CleanerNotificationBundle> bundles = new ArrayList<CleanerNotificationBundle>();

        for (Activity activity : activities) {
            Owner owner = null;
            Cleaner activity_cleaner = null;
            Mission mission = null;
            Dispute dispute = null;
            Admin admin = null;

            try {
                if (activity.getOwnerId() != 0) {
                    owner = db_conn.DAOReadOwner(activity.getOwnerId());
                }

                if (activity.getCleanerId() != 0) {
                    activity_cleaner = db_conn.DAOReadCleaner(activity.getCleanerId());
                }

                if (activity.getMissionId() != 0) {
                    mission = db_conn.DAOReadMission(activity.getMissionId());
                }

                if (activity.getDisputeId() != 0) {
                    //
                }

                if (activity.getAdminId() != 0) {
                    admin = db_conn.DAOReadAdmin(activity.getAdminId());
                }
            } catch (Exception e) {
                System.err.println(
                    "Could not fetch an element for activity (" + activity.getActivityId() + ") due to: " + e);

                continue;
            }

            bundles.add(
                new CleanerNotificationBundle(activity, owner, activity_cleaner, mission, dispute, admin));

        }

        window.setScene(new CleanerNotification(new VBox(), window, cleaner, bundles));
    }
}
