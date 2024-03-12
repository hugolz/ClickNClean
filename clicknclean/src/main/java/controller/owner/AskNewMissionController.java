package controller.owner;

import java.sql.SQLException;

import javafx.scene.control.ScrollPane;
import model.Owner;
import view.Window;
import view.owner.OwnerMissionAdd;

public class AskNewMissionController {
public AskNewMissionController(Window window, Owner owner) throws SQLException, Exception {
		
		window.setScene(new OwnerMissionAdd(new ScrollPane(), window, owner));
	}

}
