package view;

import java.io.File;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

class Dispute extends Scene {
    public Dispute(VBox container) {
        super(container, 800, 600);
        System.out.println("Dispute constructor");

        this.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
    }
}
