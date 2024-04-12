package view.cleaner;

import view.Window;
import java.io.File;

import controller.cleaner.CleanerConnected;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import model.Cleaner;

public class CleanerNotification extends Scene {
	public CleanerNotification(VBox container, Window window, Cleaner cleaner) {
		super(container, 800, 600);
		System.out.println("CleanerNotification constructor");
		Button backButton = new Button("Back");

		backButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ev) {
				try {
					new CleanerConnected(new VBox(), window, cleaner, 0);
				} catch (Exception e) {
					System.err.println("Couldn't call the next controller for error : " + e);
				}
			}
		});

		container.getChildren().add(backButton);

		this.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));

	}
}