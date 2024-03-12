package view.owner;


import view.Connection;
import view.Window;
import java.io.File;
import java.sql.SQLException;

import controller.owner.AskNewMissionController;
import controller.owner.CreatePropertyController;
import controller.owner.OwnerAskAddProperty;
import controller.owner.OwnerProfileController;
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


		MenuItem seeProfile = new MenuItem("Voir le profil");
		MenuItem disconnect = new MenuItem("Déconnexion");

		MenuItem seeProperty = new MenuItem("Voir les Propriétés");
		MenuItem addProperty = new MenuItem("Nouvelle Propriété");

		MenuItem seeMission = new MenuItem("Voir les Missions");
		MenuItem addMission = new MenuItem("Proposer une Mission");



		profile.getItems().addAll(seeProfile, disconnect);
		property.getItems().addAll(seeProperty, addProperty);
		mission.getItems().addAll(seeMission, addMission);
		bar.getMenus().addAll(profile, property, mission);

		TableView tableViewMissionCome = new TableView();

		TableColumn column1 = new TableColumn("Date et heure");
		TableColumn column2 = new TableColumn("Durée");
		TableColumn column3 = new TableColumn("Cleaner");
	    
		tableViewMissionCome.getColumns().add(column1);
	  	tableViewMissionCome.getColumns().add(column2);
	  	tableViewMissionCome.getColumns().add(column3);
		
		TableView<String> tableViewMissionInprogress = new TableView<String>();

    

		TableColumn column1p = new TableColumn("Date et heure");
		TableColumn column2p = new TableColumn("Durée");
		TableColumn column3p = new TableColumn("Cleaner");

		tableViewMissionInprogress.getColumns().add(column1p);
		tableViewMissionInprogress.getColumns().add(column2p);
		tableViewMissionInprogress.getColumns().add(column3p);





		EventHandler<ActionEvent> eventDisconnect = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				window.setScene(new Connection(new ScrollPane(), window));

				//disconnectController? see for right
			}

		};
		disconnect.setOnAction(eventDisconnect);

		EventHandler<ActionEvent> eventViewProfil = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				try {
					new OwnerProfileController(window, owner);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
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


		VBox vbox = new VBox();
		vbox.getChildren().add(bar);
		vbox.getChildren().add(welcomeMessage);
		vbox.getChildren().add(missionInProgress);
		vbox.getChildren().add(tableViewMissionInprogress);
		vbox.getChildren().add(missionCome);
		vbox.getChildren().add(tableViewMissionCome);



		tableViewMissionCome.setMaxHeight(150);
		tableViewMissionCome.setMaxWidth(500);
		tableViewMissionInprogress.setMaxHeight(150);
		tableViewMissionInprogress.setMaxWidth(500);


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

