package controller;

import view.Window;

public class AskRegistrationController {

	public AskRegistrationController(String status, Window window) {
		if (status == "Owner") {
			window.displayOwnerRegistration();

		} else if (status == "Cleaner") {
			window.displayCleanerRegistration();

		}

	}

}
