package view.admin;

import view.SceneId;
import view.Window;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Activity;
import model.ActivityType;
import model.Admin;
import model.Cleaner;
import model.CleanerExperience;
import tools.Db;

public class AdminMain extends Scene {
    public AdminMain(ScrollPane container, Window window, Admin admin) throws SQLException, Exception {
        super(container, 800, 600);
        System.out.println("AdminMain constructor");
		
		window.setTitle("Accueil d'Admin");
		
		Label welcomeMessageLabel = new Label("Bienvenue "+ admin.getSurname());
		Label confirmCleanerLabel = new Label("Cleaners en attente de confirmation :");

        ListView<String> listView = new ListView<String>();

        ArrayList<Activity> activitiesList = refreshActivities(admin.getAdminId());

        for (Activity each : activitiesList) {
            if (each.getType() == ActivityType.CLEANER_WAITING_TO_BE_CONFIRMED ) {
                Db connection = new Db();
                Cleaner currentCleaner = connection.DAOReadCleaner(each.getCleanerId());


                ObservableList<String> items = FXCollections.observableArrayList();
                items.add("Nom :" + currentCleaner.getSurname() + " " +currentCleaner.getName());
                items.add("Date de naissance : " + currentCleaner.getBirthDate());
                items.add("Experience : " + CleanerExperience.asString(currentCleaner.getExperience()));
                items.add("Motivation :  "+ currentCleaner.getMotivation());
                listView.setItems(items);
            }
        }
		
        
        
        listView.getItems().add("Cleaner 2");
        listView.getItems().add("Cleaner 3");

		Label displayDispute = new Label("Litiges à gérer");

        ListView<String> listViewSuspend = new ListView<String>();
		

        //for each...
        listViewSuspend.getItems().add("Cleaner 1");
        listViewSuspend.getItems().add("Cleaner 2");
        listViewSuspend.getItems().add("Cleaner 3");
		

		
		MenuBar bar = new MenuBar();
		Menu disconnect = new Menu("Déconnexion");
		Menu activities = new Menu("Notifications");
		
		

		bar.getMenus().addAll(disconnect, activities);
		

		
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
		
		
		VBox vbox = new VBox();
		vbox.getChildren().add(bar);
		vbox.getChildren().add(confirmCleanerLabel);
        vbox.getChildren().add(listView);
		vbox.getChildren().add(displayDispute);
        vbox.getChildren().add(listViewSuspend);
		
		
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

    public ArrayList<Activity> refreshActivities(int adminId) throws SQLException, Exception {
        Db connection = new Db();
        ArrayList<Activity> refActivities = connection.DAOReadActivities(adminId);
        connection.disconnect();
        connection = null;
        
        return refActivities;
    }
}

