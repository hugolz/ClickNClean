package view.admin;

import view.Window;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

class AdminDisputeHandling {
    public AdminDisputeHandling(Window window) {
        System.out.println("AdminDisputeHandling constructor");

        Scene scene = new Scene(new VBox(), 800, 600);
        scene.getStylesheets()
        .add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
        window.setScene(scene);
    }
}
