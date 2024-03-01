package view.owner;

import view.Window;
import view.SceneId;
import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;


public class OwnerWelcome extends Scene {
	public OwnerWelcome(ScrollPane container, Window window) {
		super(container, 800, 600);
		window.setTitle("Accueil de Propriétaire");

		Label welcomeMessage = new Label("Bienvenue");

		MenuBar bar = new MenuBar();
		Menu profile = new Menu("Profil");
		Menu property = new Menu("Propriétés");
		Menu mission = new Menu("Mission");


		MenuItem seeProfile = new MenuItem("Voir le profil");
		MenuItem disconnect = new MenuItem("Déconnexon");

		MenuItem seeProperty = new MenuItem("Voir les Propriétés");
		MenuItem addProperty = new MenuItem("Nouvelle Propriété");

		MenuItem seeMission = new MenuItem("Voir les Missions");
		MenuItem addMission = new MenuItem("Proposer une Mission");

		profile.getItems().addAll(seeProfile, disconnect);
		property.getItems().addAll(seeProperty, addProperty);
		mission.getItems().addAll(seeMission, addMission);
		bar.getMenus().addAll(profile, property, mission);

		EventHandler<ActionEvent> eventDisconnect = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				// window.setScene(SceneId.CONNECTION);
				//disconnectController? see for right
			}
		};
		disconnect.setOnAction(eventDisconnect);

		EventHandler<ActionEvent> eventViewProfil = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				//go view Ownerprofil
			}
		};
		seeProfile.setOnAction(eventViewProfil);


		VBox vbox = new VBox();
		vbox.getChildren().add(bar);
		vbox.getChildren().add(welcomeMessage);


		vbox.setSpacing(10);
		vbox.setPadding(new Insets(100, 300, 20, 300));

		container.setContent(vbox);

		container.setPannable(true);
		container.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		container.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

		this.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));

		// return scene;
	}

}

