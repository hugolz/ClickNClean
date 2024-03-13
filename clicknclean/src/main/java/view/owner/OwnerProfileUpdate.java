package view.owner;

import view.Window;
import java.io.File;
import java.sql.SQLException;

import controller.owner.OwnerProfileUpdateController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.Owner;

class OwnerProfileUpdate extends Scene {
    public OwnerProfileUpdate(ScrollPane container, Window window, Owner owner) throws SQLException, Exception {
        super(container, 800, 600);
        System.out.println("OwnerProfileUpdate constructor");
        
        Label nameOwner = new Label("Nom : "+owner.getName());
        Label surnameOwner = new Label("Prenom : "+owner.getSurname());
        Label mailOwner = new Label("Mail : "+owner.getEmail());
        Label phoneOwner = new Label("Téléphone : "+owner.getPhoneNumber());
        Label birthDateOwner = new Label("Date de naissance : "+owner.getBirthDate());
        Label motivationOwner = new Label("Type de prestation recherchée : "+owner.getServiceType().name());
        Label updatePassword = new Label("Modifier le mot de passe :");
        Button confirmUpdate = new Button("Mettre à jour le profil");
        
        EventHandler<ActionEvent> eventUpdate = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
			 try {
				//go controller
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			}

		};
		confirmUpdate.setOnAction(eventUpdate);
		  
		  
		  
        VBox vbox = new VBox();
        vbox.getChildren().add(nameOwner);
        vbox.getChildren().add(surnameOwner);
        vbox.getChildren().add(mailOwner);
        vbox.getChildren().add(phoneOwner);
        vbox.getChildren().add(birthDateOwner);
        vbox.getChildren().add(motivationOwner);
        vbox.getChildren().add(updatePassword);
        vbox.getChildren().add(confirmUpdate);

        this.getStylesheets()
        .add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
    }
}
