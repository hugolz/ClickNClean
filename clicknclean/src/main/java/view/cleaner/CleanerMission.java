
package view.cleaner;

import view.Window;
import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import model.Cleaner;

public class CleanerMission extends Scene {
	public CleanerMission(VBox container, Window window, Cleaner cleaner) {
		super(container, 800, 600);
		window.setTitle("Missions");
		System.out.println("CleanerMission constructor");
		Button backButton = new Button("Back");

		backButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ev) {
				window.setScene(new CleanerMain(new VBox(), window, cleaner));
			}
		});

		container.getChildren().add(backButton);

		this.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));

	}
}