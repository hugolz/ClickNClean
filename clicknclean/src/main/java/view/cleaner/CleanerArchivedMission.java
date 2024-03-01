package view.cleaner;

import view.Window;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

class ArchivedMission extends Scene {
    public ArchivedMission(VBox container) {
        super(container, 800, 600);
        System.out.println("Archive missions");


        this.getStylesheets()
        .add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));

    }
}
