package view.owner;

import java.io.File;
import java.sql.SQLException;

import controller.owner.OwnerProfileUpdateController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
		  Label updatePwd = new Label("Modifier le mot de passe");
		  Label updatePwd1 = new Label("Ancien mot de passe :");
		  Label updatePwd2 = new Label("Nouveau mot de passe :");
		  Label updatePwd3 = new Label("Confirmer le nouveau mot de passe :");
		  
		  Button updateOwner = new Button("Modifier");
		  Button returnview = new Button("Retour");
		  

		  PasswordField passwordInputField = new PasswordField();
		  PasswordField newPasswordInputField = new PasswordField();
		  PasswordField confirmpasswordInputField = new PasswordField();

		  
		  
		  EventHandler<ActionEvent> eventUpdate = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
				 try {
					new OwnerProfileUpdateController(window,
													owner,
													passwordInputField.getText(),
													newPasswordInputField.getText(),
													confirmpasswordInputField.getText() );
				} catch (Exception e1) {
					// TODO Auto-generated catch block 
					e1.printStackTrace();
				}

				}

			};
			updateOwner.setOnAction(eventUpdate);
			
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
		  vbox.getChildren().add(nameOwner);
		  vbox.getChildren().add(surnameOwner);
		  vbox.getChildren().add(mailOwner);
		  vbox.getChildren().add(phoneOwner);
		  vbox.getChildren().add(birthDateOwner);
		  vbox.getChildren().add(motivationOwner);
		  
		  vbox.getChildren().add(updatePwd);
		  vbox.getChildren().add(updatePwd1);
		  vbox.getChildren().add(passwordInputField);
		  vbox.getChildren().add(updatePwd2);
		  vbox.getChildren().add(newPasswordInputField);
		  vbox.getChildren().add(updatePwd3);
		  vbox.getChildren().add(confirmpasswordInputField);
		  
		  vbox.getChildren().add(updateOwner);
		  vbox.getChildren().add(returnview);
		  
		  container.setContent(vbox);
		  vbox.setSpacing(10);
			vbox.setPadding(new Insets(100, 300, 20, 300));
			vbox.setAlignment(Pos.TOP_CENTER);

			container.setPannable(true);
			container.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			container.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

			this.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
	}

}
