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
		Button profile = new Button("Profil");
		Button notification = new Button("Notification");
		Button inbox = new Button("Messages");
		Button disconnectButton = new Button("Se déconnecter");

		VBox vbox = new VBox();
		vbox.getChildren().add(profile);
		vbox.getChildren().add(notification);
		vbox.getChildren().add(inbox);

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