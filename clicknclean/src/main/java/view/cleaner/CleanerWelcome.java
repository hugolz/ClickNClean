package view.cleaner;

import java.io.File;

import javax.swing.Scrollable;

import view.Window;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import model.Cleaner;

public class CleanerWelcome extends Scene {
	public CleanerWelcome(ScrollPane container, Window window, Cleaner cleaner) {
		super(container, 800, 600);
		System.out.println("CleanerWelcome constructor");
		Button profile = new Button("Profil");
		Button notification = new Button("Notification");
		Button inbox = new Button("Messages");
		Button disconnectButton = new Button("Se déconnecter");

		VBox vbox = new VBox();
		vbox.getChildren().add(profile);
		vbox.getChildren().add(notification);
		vbox.getChildren().add(inbox);

		container.setContent(vbox);

		container.setPannable(true);
		container.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		container.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

		this.getStylesheets()
		.add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
	}
}