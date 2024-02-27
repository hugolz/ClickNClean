package view;


import java.io.File;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

class ArchivedMission {
    public ArchivedMission(Window window) {
        System.out.println("Archive missions");

        Scene scene = new Scene(new VBox(), 800, 600);
        scene.getStylesheets()
        .add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
        window.setScene(scene);
    }
}
