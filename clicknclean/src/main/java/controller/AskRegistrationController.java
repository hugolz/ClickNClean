package controller;

import view.Window;
import view.cleaner.CleanerRegistration;
import view.owner.OwnerRegistration;
import javafx.scene.control.ScrollPane;
import view.SceneId;

public class AskRegistrationController {
	public AskRegistrationController(String status, Window window) {
		if (status == "Owner") {
			window.setScene(new OwnerRegistration(new ScrollPane(), window));
		} else if (status == "Cleaner") {
			window.setScene(new CleanerRegistration(new ScrollPane(), window));
		}
	}
}
