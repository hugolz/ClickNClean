package controller.cleaner;

import javafx.scene.layout.VBox;
import model.Cleaner;
import view.Window;
import view.cleaner.CleanerMission;

public class CleanerMissionController {
    public CleanerMissionController(Window window, Cleaner cleaner) {

        window.setScene(new CleanerMission(new VBox(), window, cleaner));
    }
}
