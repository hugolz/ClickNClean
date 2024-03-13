package view.owner;

import java.io.File;
import java.sql.SQLException;

import controller.owner.OwnerProfileUpdateController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import model.Owner;
import view.Connection;
import view.Window;
import view.admin.AdminMain;

public class OwnerProfile extends Scene {
	public OwnerProfile(ScrollPane container, Window window, Owner owner)throws SQLException, Exception {
		  super(container, 800, 600);
		  
		  Label nameOwner = new Label("Nom : "+owner.getName());
		  Label surnameOwner = new Label("Prenom : "+owner.getSurname());
		  Label mailOwner = new Label("Mail : "+owner.getEmail());
		  Label phoneOwner = new Label("Téléphone : "+owner.getPhoneNumber());
		  Label birthDateOwner = new Label("Date de naissance : "+owner.getBirthDate());
		  Label motivationOwner = new Label("Type de prestation recherchée : "+owner.getServiceType().name());
		  Button updateOwner = new Button("Modifier");
		  
		  
		  EventHandler<ActionEvent> eventUpdate = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
				 try {
					new OwnerProfileUpdateController(window,owner);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				}

			};
			updateOwner.setOnAction(eventUpdate);
		  
		
		  
		  VBox vbox = new VBox();
		  vbox.getChildren().add(nameOwner);
		  vbox.getChildren().add(surnameOwner);
		  vbox.getChildren().add(mailOwner);
		  vbox.getChildren().add(phoneOwner);
		  vbox.getChildren().add(birthDateOwner);
		  vbox.getChildren().add(motivationOwner);
		  vbox.getChildren().add(updateOwner);
		  
		  container.setContent(vbox);

			container.setPannable(true);
			container.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			container.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

			this.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
	}

}
