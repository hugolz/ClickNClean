package controller.cleaner;

import javafx.scene.layout.VBox;
import model.Cleaner;
import view.Window;
public class CleanerPlanningController {
    public CleanerPlanningController(Window window, Cleaner cleaner) {

        window.setScene(new view.cleaner.CleanerPlanning(new VBox(), window, cleaner));
    }
}
