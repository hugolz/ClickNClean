package view.owner;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.owner.CreatePropertyController;
import controller.owner.OwnerCreateMissionController;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import model.Owner;
import model.Property;
import tools.Db;
import view.Window;
import javafx.scene.layout.VBox;

public class OwnerMissionAdd extends Scene{
	public OwnerMissionAdd(ScrollPane container, Window window, Owner owner)throws SQLException, Exception{
		super(container, 800, 600);
		 System.out.println("OwnerMissionAdd constructor");
		 
		 Label title = new Label("Nouvelle Mission :");
		 Label date = new Label("Date de la Mission :");
		 Label hour = new Label("Créneau Horaire :");
		 Label property = new Label("Pour quelle propriété :");
		 Button returnview = new Button("Retour");
		 
		 DatePicker dateMission = new DatePicker();
		 
		 ChoiceBox<String> hourMission = new ChoiceBox<>();
			ObservableList<String> options = FXCollections.observableArrayList();
					for(int i = 8 ; i<23 ; i++) {
						options.add(""+i);
					}		
		hourMission.setItems(options);
			
		ChoiceBox<String> minuteMission = new ChoiceBox<>();
			ObservableList<String> options1 = FXCollections.observableArrayList();
			for(int j = 0 ; j<60 ; j=j+5) {
				options1.add(""+j);
			}		
		minuteMission.setItems(options1);
		
		ChoiceBox<String> propertyOwner = new ChoiceBox<>();
			ObservableList<String> options2 = FXCollections.observableArrayList();
			Db db = new Db();
			ArrayList<Property> prop = db.DAOReadOwnerProperties(owner.getOwnerID());
			for (int i = 0; i < prop.size(); i++) {
			options2.add(""+prop.get(i).getPropertyAddress().asString());
			}
			
			propertyOwner.setItems(options2);
			
		Button addMission = new Button("Confirmer");
		
		 EventHandler<ActionEvent> eventaddMission = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
				for (int i = 0; i < prop.size(); i++) {
					if(propertyOwner.getValue().equals(prop.get(i).getPropertyAddress().asString())) {
						 
						 try {
								new OwnerCreateMissionController(dateMission.getValue(),
																Integer.valueOf(hourMission.getValue()), 
																Integer.valueOf(minuteMission.getValue()), 
																prop.get(i),
																window,
																owner);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
					}
				}
				}

			};
			addMission.setOnAction(eventaddMission);
			
			EventHandler<ActionEvent> eventReturn = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					try {
						window.setScene(new OwnerMain(new ScrollPane(), window, owner));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			};
			returnview.setOnAction(eventReturn);
		 
		 VBox vbox = new VBox();
		 vbox.getChildren().add(title);
		 vbox.getChildren().add(date);
		 vbox.getChildren().add(dateMission);
		 vbox.getChildren().add(hour);
		 vbox.getChildren().add(hourMission);
		 vbox.getChildren().add(minuteMission);
		 vbox.getChildren().add(property);
		 vbox.getChildren().add(propertyOwner);
		 vbox.getChildren().add(addMission);
		 vbox.getChildren().add(returnview);
		 
		 
		 
		 
		 
		 
		 
		 vbox.setSpacing(10);
		 vbox.setPadding(new Insets(100, 300, 20, 300));
		 vbox.setAlignment(Pos.TOP_CENTER);


		 container.setContent(vbox);

		 container.setPannable(true);
		 container.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		 container.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

		 this.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
		
	}

}
