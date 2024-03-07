package view.owner;

import view.Window;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

class OwnerPropertyAdd extends Scene {
    public OwnerPropertyAdd(VBox container, Window window ) {
        super(container, 800, 600);
        System.out.println("OwnerPropertyAdd constructor");

        Label title = new Label("Ajout d'une propriété");

        this.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
    }
}
