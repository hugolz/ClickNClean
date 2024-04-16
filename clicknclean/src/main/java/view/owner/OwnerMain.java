package view.owner;


import view.Connection;
import view.Window;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

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
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import model.Cleaner;
import model.Mission;
import model.Owner;
import tools.Db;

public class OwnerMain extends Scene {
	public OwnerMain(ScrollPane container, Window window, Owner owner) throws SQLException, Exception {
		super(container, 800, 600);
		System.out.println("OwnerMain constructor");

		Db db = new Db();
		VBox vbox = new VBox();


		Label welcomeMessage = new Label("Bienvenu " + owner.getName() + " !");

		Label missionInProgress = new Label("Mission en attente de Cleaner :");
		Label missionCome = new Label("Vos missions à venir");


		MenuBar bar = new MenuBar();
		Menu profile = new Menu("Profil");
		Menu property = new Menu("Propriétés");
		Menu mission = new Menu("Mission");
		Menu dispute = new Menu("Litige");
		Menu notif = new Menu("Notification");


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
		bar.getMenus().addAll(profile, property, mission, dispute, notif);


		ArrayList<Mission> missionProp = db.DAOReadMissionOwner1(owner.getOwnerID());




		//listview for mission to come
		ListView<String> listView = new ListView<String>();



		//listview confirm mission completed by the cleaner


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




		vbox.getChildren().add(bar);
		vbox.getChildren().add(welcomeMessage);
		vbox.getChildren().add(missionInProgress);


		for (int i = 0; i < missionProp.size(); i++) {
			System.out.println("test boucle 1 " + i);
			Mission m = missionProp.get(i) ;

			ArrayList<Cleaner> cleaners = db.DAOReadMissionProposal(m.getMissionId());
			vbox.getChildren().add(
			    new Label(
			        "Mission : " + i +
			        "\nDate : " + m.getMissionDate() +
			        "\nDurée : " + m.getDuration() + " h" +
			        "\nPrix : " + m.getCost() + " €" +
			        "\nPropriété : " + m.getProperty().getPropertyAddress().asString()
			    )
			);
			MenuButton menuButton = new MenuButton("Sélectionnez un Cleaner");
			Menu cleanerBlockContainer = new Menu();
			for (int j = 0; j < cleaners.size(); j++) {
				System.out.println("test boucle 2" + j);
				Cleaner c = cleaners.get(j);

				MenuItem mi = new MenuItem();
				Button b = new Button("" + c.getCleanerId());

				b.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent e) {
						// TODO: call a controller to set the mission's cleaner to j

						new controller.owner.OwnerSetMissionCleaner(window, owner, m.getMissionId(), c.getCleanerId());
					}
				});

				mi.setGraphic(b);

				cleanerBlockContainer.getItems().add(mi);
			}
			menuButton.getItems().add(cleanerBlockContainer);
			vbox.getChildren().add(menuButton);
		}

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

