package controller.cleaner;

import javafx.scene.layout.VBox;
import model.Cleaner;
import view.Window;
import view.cleaner.CleanerNotification;

public class CleanerNotificationController {
    public CleanerNotificationController(Window window, Cleaner cleaner) {

        window.setScene(new CleanerNotification(new VBox(), window, cleaner));
    }
}
