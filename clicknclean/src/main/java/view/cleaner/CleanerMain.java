package view.cleaner;

import view.Window;
import model.Cleaner;

import java.io.File;

import javax.swing.Scrollable;

import controller.LogoutController;
import controller.cleaner.CleanerInboxController;
import controller.cleaner.CleanerNotificationController;
import controller.cleaner.CleanerProfileController;
import controller.cleaner.CleanerPlanningController;
import controller.cleaner.CleanerMissionController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CleanerMain extends Scene {
	public CleanerMain(VBox container, Window window, Cleaner cleaner) {
		super(container, 800, 600);
		System.out.println("CleanerWelcome constructor");
		Button profileButton = new Button("Profil");
		Button notificationButton = new Button("Notification");
		Button inboxButton = new Button("Messages");
		Button disconnectButton = new Button("Se déconnecter");
		Button planningButton = new Button("View planning");
		Button archivedMissionsButton = new Button("View old missions");

		profileButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent ev) {
				new CleanerProfileController(window, cleaner);
			}
		});
		notificationButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent ev) {
				new CleanerNotificationController(window, cleaner);
			}
		});
		inboxButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent ev) {
				new CleanerInboxController(window, cleaner);
			}
		});
		disconnectButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent ev) {
				new LogoutController(window, cleaner.getCleanerId());
			}
		});
		planningButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent ev) {
				new CleanerPlanningController(window, cleaner);
			}
		});
		archivedMissionsButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent ev) {
				new CleanerMissionController(window, cleaner);
			}
		});

		VBox header = new VBox();
		header.setId("header");
		// header.getStyleClass().addAll("hbox");
		header.getChildren().addAll(profileButton, notificationButton, inboxButton);


		container.getChildren().addAll(header, planningButton, archivedMissionsButton, disconnectButton);

		// container.setPannable(true);
		// container.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		// container.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

		this.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
		this.getStylesheets()
		.add("file:///" + new File("src/main/css/cleaner_welcome.css").getAbsolutePath().replace("\\", "/"));
	}
}