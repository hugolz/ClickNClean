package controller.owner;

import java.sql.SQLException;

import javafx.scene.control.ScrollPane;
import model.Owner;
import view.Window;
import view.owner.OwnerMain;
import view.owner.OwnerProfile;

public class OwnerProfileController {
	public OwnerProfileController(Window window, Owner owner) {

		try {

			window.setScene(new OwnerProfile(new ScrollPane(), window, owner));
		} catch (Exception e) {
			System.out.println("[ERROR] " + e  );
		}
	}

}
