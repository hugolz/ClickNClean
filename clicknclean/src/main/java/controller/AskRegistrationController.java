package controller;

import view.Window;
import view.SceneId;

public class AskRegistrationController {
	public AskRegistrationController(String status, Window window) {
		if (status == "Owner") {
			// window.displayOwnerRegistration();
			window.setScene(SceneId.OWNER_REGISTRATION);
		} else if (status == "Cleaner") {
			// window.displayCleanerRegistration();
			window.setScene(SceneId.CLEANER_REGISTRATION);

		}
	}
}
