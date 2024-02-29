package view.owner;

import view.Window;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

class OwnerProfileUpdate extends Scene {
    public OwnerProfileUpdate(VBox container) {
        super(container, 800, 600);
        System.out.println("OwnerProfileUpdate constructor");

        this.getStylesheets()
        .add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
    }
}
