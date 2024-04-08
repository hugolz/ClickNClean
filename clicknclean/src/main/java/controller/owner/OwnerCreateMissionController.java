package controller.owner;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.JOptionPane;

import javafx.scene.control.ScrollPane;
import model.Mission;
import model.Owner;
import model.Property;
import tools.Db;
import view.Window;
import view.owner.OwnerMain;
import view.owner.OwnerMission;

public class OwnerCreateMissionController {

	public OwnerCreateMissionController(LocalDate date, int hour, int minute, Property property, Window window, Owner owner) throws SQLException, Exception {

		Db db = new Db();
		int idMission;
		
		if (date==null || hour==0 || minute==34 || property==null) {

			JOptionPane.showMessageDialog(null, "Champs non remplis !");
			return;
		}		
		
		LocalTime time = LocalTime.parse(hour+":"+minute+":00.00");
		LocalDateTime dateTime = LocalDateTime.parse(date+"T"+time);

		if (date.isBefore(LocalDate.now())) {
			JOptionPane.showMessageDialog(null, "La date est déjà passée !");
			return;
		}

		try {

		idMission = db.DAOCreateNewMission(property, dateTime); 
		}
		catch (Exception e) {

			JOptionPane.showMessageDialog(null, "L'ajout de mission a échoué");
			return;
		}
		JOptionPane.showMessageDialog(null, "Ajout réussi ! \nVous allez être redirigez vers la page des Missions !");
		Mission mission = db.DAOReadMission(idMission);
		window.setScene(new OwnerMission(new ScrollPane(), window, owner));
		db.disconnect();


	}

}
