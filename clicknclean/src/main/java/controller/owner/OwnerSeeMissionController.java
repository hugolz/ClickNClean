package controller.owner;

import java.sql.SQLException;

import javafx.scene.control.ScrollPane;
import model.Mission;
import model.Owner;
import view.Window;
import view.owner.OwnerMission;

public class OwnerSeeMissionController{
	public OwnerSeeMissionController(Window window, Owner owner) throws SQLException, Exception {
		
		window.setScene(new OwnerMission(new ScrollPane(), window, owner));
		
	}

}
