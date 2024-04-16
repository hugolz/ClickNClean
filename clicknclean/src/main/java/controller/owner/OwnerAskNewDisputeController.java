package controller.owner;

import java.sql.SQLException;

import javafx.scene.control.ScrollPane;
import model.Owner;
import view.Window;
import view.owner.OwnerDisputeAdd;


public class OwnerAskNewDisputeController {
public OwnerAskNewDisputeController(Window window, Owner owner) throws SQLException, Exception {
		
		window.setScene(new OwnerDisputeAdd(new ScrollPane(), window, owner));
	}

}
