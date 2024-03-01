package view.owner;

import view.SceneId;
import view.Window;
import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import model.Owner;

public class OwnerMain extends Scene {
    public OwnerMain(ScrollPane container, Window window, Owner owner) {
        super(container, 800, 600);
        System.out.println("OwnerMain constructor");
		
		window.setTitle("Acceuil de Propriétaire");
		
		Label welcomeMessage = new Label("Bienvenu "+Window.currentOwner.getName());
		Label missionInProgress = new Label("Vos missions en cours :");
		Label missionCome = new Label("Vos missions à venir");
		
		ListView<String> listView = new ListView<String>();
		
        listView.getItems().add("Item 1");
        listView.getItems().add("Item 2");
        listView.getItems().add("Item 3");
		
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
				//window.setScene(SceneId.CONNECTION);
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
		vbox.getChildren().add(missionInProgress);
		vbox.getChildren().add(listView);
		
		listView.setMaxHeight(100);
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

