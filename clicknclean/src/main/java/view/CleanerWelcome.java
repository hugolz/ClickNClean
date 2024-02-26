package view;

import java.io.File;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;

public class CleanerWelcome {
	public CleanerWelcome(Window window) {
		System.out.println("CleanerWelcome constructor");
		Button registerProfil = new Button("Profil");
		Button registerNotifications = new Button("Notification");
		Button registerMessages = new Button("Messages");
		Button retour = new Button("Retour");

		// Mission

		// Planning

		VBox vbox = new VBox();
		vbox.getChildren().add(registerProfil);
		vbox.getChildren().add(registerNotifications);
		vbox.getChildren().add(registerMessages);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(vbox);

		scrollPane.setPannable(true);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

		Scene scene = new Scene(scrollPane, 800, 600);
		scene.getStylesheets()
		.add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
		window.setScene(scene);
	}
}