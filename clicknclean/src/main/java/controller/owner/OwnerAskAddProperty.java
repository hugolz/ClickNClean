package controller.owner;

import java.sql.SQLException;

import javafx.scene.control.ScrollPane;
import model.Owner;
import view.Window;
import view.owner.OwnerPropertyAdd;

public class OwnerAskAddProperty {
	public OwnerAskAddProperty(Window window, Owner owner) throws SQLException, Exception{
		window.setScene(new OwnerPropertyAdd(new ScrollPane(), window, owner));
		
	}

}
