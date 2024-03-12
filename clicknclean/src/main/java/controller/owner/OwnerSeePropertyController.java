package controller.owner;

import java.sql.SQLException;

import javafx.scene.control.ScrollPane;
import model.Owner;
import view.Window;
import view.owner.OwnerProperty;

public class OwnerSeePropertyController {
public OwnerSeePropertyController(Window window, Owner owner) throws SQLException, Exception {
		
		window.setScene(new OwnerProperty(new ScrollPane(), window, owner));
	}

}
