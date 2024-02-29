package view.admin;

import view.Window;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

class AdminDisputeHandling extends Scene {
    public AdminDisputeHandling(VBox container) {
        super(container, 800, 600);
        System.out.println("AdminDisputeHandling constructor");

        this.getStylesheets()
        .add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));

    }
}
