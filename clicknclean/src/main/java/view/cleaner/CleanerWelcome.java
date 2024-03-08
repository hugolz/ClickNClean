package view.cleaner;

import view.Window;
import model.Cleaner;

import java.io.File;

import javax.swing.Scrollable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CleanerWelcome extends Scene {
	public CleanerWelcome(ScrollPane container, Window window, Cleaner cleaner) {
		super(container, 800, 600);
		System.out.println("CleanerWelcome constructor");
		Button profile = new Button("Profil");
		Button notification = new Button("Notification");
		Button inbox = new Button("Messages");
		Button disconnectButton = new Button("Se déconnecter");
		Button missionCreateButton = new Button("Create new mission");
		Button missionUpdateButton = new Button("Update a mission");
		Button propertyAddButton = new Button("Add a property");
		Button propertyUpdateButton = new Button("Modify a property");
		Button viewChatsButton = new Button("Open chats");
		Button profileUpdateButton = new Button("Modify profile");

		profile.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ev) {
				System.out.println("profile button clicked with event: " + ev);
			}
		});
		notification.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ev) {
				System.out.println("notification button clicked with event: " + ev);
			}
		});
		inbox.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ev) {
				System.out.println("inbox button clicked with event: " + ev);
			}
		});
		disconnectButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ev) {
				System.out.println("disconnectButton button clicked with event: " + ev);
			}
		});
		missionCreateButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ev) {
				System.out.println("missionCreateButton button clicked with event: " + ev);
			}
		});
		missionUpdateButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ev) {
				System.out.println("missionUpdateButton button clicked with event: " + ev);
			}
		});
		propertyAddButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ev) {
				System.out.println("propertyAddButton button clicked with event: " + ev);
			}
		});
		propertyUpdateButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ev) {
				System.out.println("propertyUpdateButton button clicked with event: " + ev);
			}
		});
		viewChatsButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ev) {
				System.out.println("viewChatsButton button clicked with event: " + ev);
			}
		});
		profileUpdateButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ev) {
				System.out.println("profileUpdateButton button clicked with event: " + ev);
			}
		});

		HBox header = new HBox();
		header.setId("header");
		header.getStyleClass().addAll("hbox");
		header.getChildren().addAll(profile, notification, inbox);

		VBox global = new VBox();
		global.getChildren().add(header);

		global.getChildren().addAll(missionCreateButton, missionUpdateButton, propertyAddButton,
		                            propertyUpdateButton, viewChatsButton, profileUpdateButton);

		container.setContent(global);

		container.setPannable(true);
		container.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		container.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

		this.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
		this.getStylesheets()
		.add("file:///" + new File("src/main/css/cleaner_welcome.css").getAbsolutePath().replace("\\", "/"));
	}
}