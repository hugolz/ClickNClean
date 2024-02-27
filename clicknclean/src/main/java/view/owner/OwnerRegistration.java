package view.owner;

import java.io.File;

import view.SceneId;
import view.Window;
import controller.AskRegistrationController;
import controller.ConnectionController;
import controller.owner.OwnerRegistrationController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.OwnerMotivation;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.DatePicker;

public class OwnerRegistration {
	public OwnerRegistration(Window window) {
		System.out.println("OwnerRegistration constructor");
		window.setTitle("Inscription");

		Label title = new Label("S'inscire en tant que Demandeur :");
		Label nameLabel = new Label("Nom :");
		Label surnameLabel = new Label("Prénom :");
		Label emailLabel = new Label("Mail :");
		Label passwordLabel = new Label("Mot de passe :");
		Label confirmpasswordLabel = new Label("Confirmer le mot de passe :");
		Label phoneLabel = new Label("Téléphone :");
		Label birthDateLabel = new Label("Date de naissance :");
		Label ownerMotivationLabel = new Label("Type de prestation recherché :");
		Button registerButton = new Button("Inscription");
		Button returnview = new Button("Retour");

		TextField nameInputField = new TextField();
		TextField surnameInputField = new TextField();
		TextField emailInputField = new TextField();
		PasswordField passwordInputField = new PasswordField();
		PasswordField confirmpasswordInputField = new PasswordField();
		TextField phoneInputField = new TextField();
		DatePicker birthDateInputField = new DatePicker();
		ChoiceBox<String> ownerMotivationChoiceBox = new ChoiceBox<>();

		ObservableList<String> options = FXCollections.observableArrayList(
		                                     "Résidence principale", "Courte durée", "Etat des lieux");
		ownerMotivationChoiceBox.setItems(options);

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				OwnerMotivation om = null;
				switch (ownerMotivationChoiceBox.getValue()) {
				case "Résidence principale":
					om = OwnerMotivation.MAIN_HOME;
					break;
				case "Courte durée":
					om = OwnerMotivation.GUEST_ROOM;
					break;
				case "Etat des lieux":
					om = OwnerMotivation.INVENTORY;
					break;
				}

				new OwnerRegistrationController(
				    nameInputField.getText(),
				    surnameInputField.getText(),
				    emailInputField.getText(),
				    passwordInputField.getText(),
				    confirmpasswordInputField.getText(),
				    phoneInputField.getText(),
				    birthDateInputField.getValue(),
				    om,
				    window);
			}
		};

		registerButton.setOnAction(event);

		EventHandler<ActionEvent> eventReturn = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				// window.displayConnectionView();
				window.setScene(SceneId.CONNECTION);
			}

		};
		returnview.setOnAction(eventReturn);

		VBox vbox = new VBox();
		vbox.getChildren().add(title);
		vbox.getChildren().add(nameLabel);
		vbox.getChildren().add(nameInputField);
		vbox.getChildren().add(surnameLabel);
		vbox.getChildren().add(surnameInputField);
		vbox.getChildren().add(emailLabel);
		vbox.getChildren().add(emailInputField);
		vbox.getChildren().add(passwordLabel);
		vbox.getChildren().add(passwordInputField);
		vbox.getChildren().add(confirmpasswordLabel);
		vbox.getChildren().add(confirmpasswordInputField);
		vbox.getChildren().add(phoneLabel);
		vbox.getChildren().add(phoneInputField);
		vbox.getChildren().add(birthDateLabel);
		vbox.getChildren().add(birthDateInputField);
		vbox.getChildren().add(ownerMotivationLabel);
		vbox.getChildren().add(ownerMotivationChoiceBox);
		vbox.getChildren().add(registerButton);
		vbox.getChildren().add(returnview);

		vbox.setSpacing(10);
		vbox.setPadding(new Insets(100, 300, 20, 300));

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(vbox);

		scrollPane.setPannable(true);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

		nameLabel.setMaxWidth(Double.MAX_VALUE);
		surnameLabel.setMaxWidth(Double.MAX_VALUE);
		registerButton.setMaxWidth(Double.MAX_VALUE);
		VBox.setVgrow(nameLabel, Priority.ALWAYS);
		VBox.setVgrow(surnameLabel, Priority.ALWAYS);
		VBox.setVgrow(registerButton, Priority.ALWAYS);

		Scene scene = new Scene(scrollPane, 800, 600);
		scene.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
		window.setScene(scene);
	}
}