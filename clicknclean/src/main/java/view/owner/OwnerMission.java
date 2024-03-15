package view.owner;

import java.io.File;
import java.sql.SQLException;

import controller.owner.AskNewMissionController;
import controller.owner.OwnerAskAddProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import model.Owner;
import view.Window;

public class OwnerMission extends Scene {
	public OwnerMission(ScrollPane container, Window window, Owner owner)throws SQLException, Exception {
		 super(container, 800, 600);
		 
		 System.out.println("OwnerMission constructor");
		 
		 Label title = new Label("Vos Missions :");
		 Button addMission = new Button("Proposer une mission");
		 Button returnview = new Button("Retour");
		 
		 
		 
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
