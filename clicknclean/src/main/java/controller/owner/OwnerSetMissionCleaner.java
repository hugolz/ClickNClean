package controller.owner;

import java.sql.SQLException;

import javafx.scene.control.ScrollPane;
import model.Owner;
import view.Window;
import view.owner.OwnerMain;
import tools.Db;


public class OwnerSetMissionCleaner {
	public OwnerSetMissionCleaner(Window window, Owner owner, int missionId, int cleanerId) {
		Db db = new Db();

		try {
			db.DAOOwnerMissionSetCleaner(missionId, cleanerId);
		} catch (Exception e) {
			System.err.println("[ERROR] Failed to set cleaner for mission (" + missionId + ") due to: " + e);
		}

		try {

			window.setScene(new OwnerMain(new ScrollPane(), window, owner));
		} catch (Exception e) {
			System.err.println("[ERROR] Could not set the OwnerMain view due to: " + e + ", failling back to Connection");
			window.setScene(new view.Connection(new ScrollPane(), window));
		}
	}

}
