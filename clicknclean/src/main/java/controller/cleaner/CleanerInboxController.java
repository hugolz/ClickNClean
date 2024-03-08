package controller.cleaner;

import javafx.scene.layout.VBox;
import model.Cleaner;
import view.Window;
public class CleanerInboxController {
	public CleanerInboxController(Window window, Cleaner cleaner) {

		window.setScene(new view.cleaner.CleanerInbox(new VBox(), window, cleaner));
	}
}
