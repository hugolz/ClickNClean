package view.cleaner;

import view.Window;
import model.Cleaner;
import model.Mission;
import tools.MissionDisplay;

import java.io.File;
import java.util.ArrayList;

import javax.swing.Scrollable;

import controller.LogoutController;
import controller.cleaner.CleanerNotificationController;
import controller.cleaner.CleanerProfileController;
import controller.cleaner.CleanerPlanningController;
import controller.cleaner.CleanerConnected;
import controller.cleaner.CleanerMissionController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CleanerMain extends Scene {
	public CleanerMain(VBox container, Window window, Cleaner cleaner, ArrayList<MissionDisplay> missions) {
		super(container, 800, 600);
		window.setTitle("Main menu");
		System.out.println("CleanerWelcome constructor");
		Button profileButton = new Button("Profil");
		Button notificationButton = new Button("Notification");
		Button disconnectButton = new Button("Se déconnecter");
		Button planningButton = new Button("View planning");
		Button archivedMissionsButton = new Button("View old missions");
		ListView<Pane> viewsContainer = new ListView<>();
		
		if  (!missions.isEmpty()) {
			for (MissionDisplay currentMission : missions) {
				VBox oneViewContainer = new VBox();
				Button proposeMission = new Button("Proposer mes services");
				Text missionContent = new Text("Mission de " + currentMission.getOwnerFirstName() + ":"
					+ "\n" + currentMission.getDuration() + "heure(s), le " + currentMission.getDate());
				oneViewContainer.getChildren().addAll(missionContent, proposeMission);
				viewsContainer.getItems().add(oneViewContainer);

				proposeMission.setOnAction(new EventHandler<ActionEvent> () {
					public void handle(ActionEvent ev) {
						try {
							new CleanerConnected(new VBox(), window, cleaner, currentMission.getMissionId());
						} catch (Exception e) {
							System.err.println("Error");
						}
					}
				});
			}
		}

		profileButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent ev) {
				window.setScene(new CleanerProfile(new VBox(), window, cleaner));
			}
		});
		notificationButton.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent ev) {
				new CleanerNotificationController(window, cleaner);
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
	
		VBox mainField = new VBox();
		mainField.getChildren().add(viewsContainer);	
		header.getChildren().addAll(profileButton, notificationButton);
		mainField.setSpacing(10);
		container.getChildren().addAll(header, planningButton, archivedMissionsButton, disconnectButton, mainField);

		this.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
		this.getStylesheets()
		.add("file:///" + new File("src/main/css/cleaner_welcome.css").getAbsolutePath().replace("\\", "/"));
	}
}