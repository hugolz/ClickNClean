package view;

import java.io.File;

import controller.ConnectionController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Owner_registration extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Registration");

		Label nameLabel = new Label("Nom :");
		Label surnameLabel = new Label("Prénom :");
		Label emailLabel = new Label("Mail :");
		Label phoneLabel = new Label("Téléphone :");
		Label birthDateLabel = new Label("Date de naissance :");
		Label ownerMotivationLabel = new Label("Type de prestation recherché :");
		Button registerButton = new Button("Inscription");

		
		TextField nameInputField = new TextField();
		TextField surnameInputField = new TextField();
		TextField emailInputField = new TextField();
		TextField phoneInputField = new TextField();
		TextField birthDateInputField = new TextField();
		ChoiceBox<String> ownerMotivationChoiceBox = new ChoiceBox<>();

		ObservableList<String> options = FXCollections.observableArrayList(
                "Résidence principale", "Courte durée", "Etat des lieux"
		);
		ownerMotivationChoiceBox.setItems(options);

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
			//	new ConnectionController(
			//		nameInputField.getText(), 
			//		surnameInputField.getText(),
			//		emailInputField.getText(), 
			//		phoneInputField.getText(),
			//		birthDateInputField.getText(), 
			//		ownerMotivationChoiceBox.getValue();
			//		);
			}
		};

		registerButton.setOnAction(event);

		VBox vbox = new VBox();
		vbox.getChildren().add(nameLabel);
		vbox.getChildren().add(nameInputField);
		vbox.getChildren().add(surnameLabel);
		vbox.getChildren().add(surnameInputField);
		vbox.getChildren().add(emailLabel);
		vbox.getChildren().add(emailInputField);
		vbox.getChildren().add(phoneLabel);
		vbox.getChildren().add(phoneInputField);
		vbox.getChildren().add(birthDateLabel);
		vbox.getChildren().add(birthDateInputField);
		vbox.getChildren().add(ownerMotivationLabel);
		vbox.getChildren().add(ownerMotivationChoiceBox);
		vbox.getChildren().add(registerButton);

		vbox.setSpacing(10);
		vbox.setPadding(new Insets(100, 300, 20, 300));

		nameLabel.setMaxWidth(Double.MAX_VALUE);
		surnameLabel.setMaxWidth(Double.MAX_VALUE);
		registerButton.setMaxWidth(Double.MAX_VALUE);
		VBox.setVgrow(nameLabel, Priority.ALWAYS);
		VBox.setVgrow(surnameLabel, Priority.ALWAYS);
		VBox.setVgrow(registerButton, Priority.ALWAYS);

		Scene scene = new Scene(vbox, 800, 600);
		scene.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}