package view.admin;

import view.Window;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

class AdminMain {
    public AdminMain(Window window) {
        System.out.println("AdminMain constructor");

        Scene scene = new Scene(new VBox(), 800, 600);
        scene.getStylesheets()
        .add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
        window.setScene(scene);
    }
}
