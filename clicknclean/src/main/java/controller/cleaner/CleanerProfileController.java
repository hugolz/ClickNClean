package controller.cleaner;

import javafx.scene.layout.VBox;
import model.Cleaner;
import view.Window;
import view.cleaner.CleanerProfile;

public class CleanerProfileController {
    public CleanerProfileController(Window window, Cleaner cleaner) {

        window.setScene(new CleanerProfile(new VBox(), window, cleaner));

    }
}
