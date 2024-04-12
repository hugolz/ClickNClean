package controller.owner;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.JOptionPane;

import javafx.scene.control.ScrollPane;
import model.ActivityType;
import model.Cleaner;
import model.Mission;
import model.Owner;
import model.Property;
import tools.Db;
import view.Window;
import view.owner.OwnerMain;

public class OwnerCreateMissionController {

	public OwnerCreateMissionController(LocalDate date, int int_hour, int int_minute, Property property, Window window,
	                                    Owner owner) throws SQLException, Exception {

		Db db = new Db();

		if (date == null || int_hour == 0 || int_minute == 34 || property == null) {

			JOptionPane.showMessageDialog(null, "Champs non remplis !");
			return;
		}

		String hour = "" + int_hour;
		String minute = "" + int_minute;

		if (hour.length() == 1) {
			hour = "0" + hour;
		}

		if (minute.length() == 1) {
			minute = "0" + minute;
		}
		LocalTime time = LocalTime.parse(hour + ":" + minute + ":00");
		LocalDateTime dateTime = LocalDateTime.parse("" + date + "T" + time + "");

		if (date.isBefore(LocalDate.now())) {
			JOptionPane.showMessageDialog(null, "La date est déjà passée !");
			return;
		}

		int missionId;
		try {
			missionId = db.DAOCreateNewMission(property, dateTime);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "[ERROR] Mission creation failled due to: " + e + ".");
			return;
		}

		JOptionPane.showMessageDialog(null, "Ajout réussi ! Vous allez être redirigez vers la page d'acceuil !");

		for (Integer id_cleaner : db.DAOListCleaners()) {
			try {
				db.DAOaddActivity(ActivityType.NEW_MISSION_AVAILABLE, id_cleaner, null, null, missionId, null, null);
			} catch (Exception e) {
				System.err.println("[ERROR] Could not create NEW_MISSION_AVAILABLE activity for cleaner " + id_cleaner
				                   + " due to: " + e + ".");
			}
		}
		window.setScene(new OwnerMain(new ScrollPane(), window, owner));
		db.disconnect();

	}

}
