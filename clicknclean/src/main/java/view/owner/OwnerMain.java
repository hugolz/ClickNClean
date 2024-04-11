package view.owner;


import view.Connection;
import view.Window;
import java.io.File;
import java.sql.SQLException;

import controller.owner.AskNewMissionController;
import controller.owner.CreatePropertyController;
import controller.owner.OwnerAskAddProperty;
import controller.owner.OwnerAskNewDisputeController;
import controller.owner.OwnerProfileController;
import controller.owner.OwnerSeeMissionController;
import controller.owner.OwnerSeePropertyController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import model.Owner;

public class OwnerMain extends Scene {
	public OwnerMain(ScrollPane container, Window window, Owner owner) throws SQLException, Exception {
		super(container, 800, 600);
		System.out.println("OwnerMain constructor");

		Label welcomeMessage = new Label("Bienvenu " + owner.getName() + " !");

		Label missionInProgress = new Label("Vos missions en cours :");
		Label missionCome = new Label("Vos missions à venir");

		MenuBar bar = new MenuBar();
		Menu profile = new Menu("Profil");
		Menu property = new Menu("Propriétés");
		Menu mission = new Menu("Mission");
		Menu dispute = new Menu("Litige");


		MenuItem seeProfile = new MenuItem("Voir le profil");
		MenuItem disconnect = new MenuItem("Déconnexion");

		MenuItem seeProperty = new MenuItem("Voir les Propriétés");
		MenuItem addProperty = new MenuItem("Nouvelle Propriété");

		MenuItem seeMission = new MenuItem("Voir les Missions");
		MenuItem addMission = new MenuItem("Proposer une Mission");

		MenuItem addDispute = new MenuItem("Commencer un litige");

		profile.getItems().addAll(seeProfile, disconnect);
		property.getItems().addAll(seeProperty, addProperty);
		mission.getItems().addAll(seeMission, addMission);
		dispute.getItems().addAll(addDispute);
		bar.getMenus().addAll(profile, property, mission, dispute);

		EventHandler<ActionEvent> eventDisconnect = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				window.setScene(new Connection(new ScrollPane(), window));

				//disconnectController? see for right
			}

		};
		disconnect.setOnAction(eventDisconnect);

		EventHandler<ActionEvent> eventViewProfil = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				new OwnerProfileController(window, owner);
			}
		};
		seeProfile.setOnAction(eventViewProfil);

		EventHandler<ActionEvent> eventSeeProperty = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					new OwnerSeePropertyController(window, owner);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		};
		seeProperty.setOnAction(eventSeeProperty);

		EventHandler<ActionEvent> eventAddProperty = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					new OwnerAskAddProperty(window, owner);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		};
		addProperty.setOnAction(eventAddProperty);

		EventHandler<ActionEvent> eventNewMission = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					new AskNewMissionController(window, owner);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		};
		addMission.setOnAction(eventNewMission);

		EventHandler<ActionEvent> eventSeeMission = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					new OwnerSeeMissionController(window, owner);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		};
		seeMission.setOnAction(eventSeeMission);


		EventHandler<ActionEvent> eventAddDispute = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					new OwnerAskNewDisputeController(window, owner);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		};
		addDispute.setOnAction(eventAddDispute);


		VBox vbox = new VBox();
		vbox.getChildren().add(bar);
		vbox.getChildren().add(welcomeMessage);
		vbox.getChildren().add(missionInProgress);
		vbox.getChildren().add(missionCome);

		vbox.setSpacing(10);
		vbox.setPadding(new Insets(100, 300, 20, 300));
		vbox.setAlignment(Pos.TOP_CENTER);

		container.setContent(vbox);

		container.setPannable(true);
		container.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		container.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);


		this.getStylesheets()
		.add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
	}
}

