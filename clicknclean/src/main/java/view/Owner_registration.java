package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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

		Label etiquette1 = new Label("Nom :");
		Label etiquette2 = new Label("Prénom :");
		Label etiquette3 = new Label("Mail :");
		Label etiquette4 = new Label("Téléphone :");
		Label etiquette5 = new Label("LocalDate de naissance :");
		Label etiquette6 = new Label("Type de prestation recherché :");
		Button button = new Button("Register");


		TextField nomField = new TextField();
		TextField prenomField = new TextField();
		TextField mailField = new TextField();
		TextField telephoneField = new TextField();
		TextField dateDeNaissanceField = new TextField();
		TextField typePrestationRechercheField = new TextField();

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				// new ConnectionController(nomField.getText(), prenomField.getText(),mailField.getText(), telephoneField.getText(),dateDeNaissanceField.getText(), typePrestationRechercheField.getText());
			}
		};

		button.setOnAction(event);

		VBox vbox = new VBox();
		vbox.getChildren().add(etiquette1);
		vbox.getChildren().add(nomField);
		vbox.getChildren().add(etiquette2);
		vbox.getChildren().add(prenomField);
		vbox.getChildren().add(etiquette3);
		vbox.getChildren().add(mailField);
		vbox.getChildren().add(etiquette4);
		vbox.getChildren().add(telephoneField);
		vbox.getChildren().add(etiquette5);
		vbox.getChildren().add(dateDeNaissanceField);
		vbox.getChildren().add(etiquette6);
		vbox.getChildren().add(typePrestationRechercheField);
		vbox.getChildren().add(button);

		vbox.setSpacing(10);
		vbox.setPadding(new Insets(100, 300, 20, 300));

		etiquette1.setMaxWidth(Double.MAX_VALUE);
		etiquette2.setMaxWidth(Double.MAX_VALUE);
		button.setMaxWidth(Double.MAX_VALUE);
		VBox.setVgrow(etiquette1, Priority.ALWAYS);
		VBox.setVgrow(etiquette2, Priority.ALWAYS);
		VBox.setVgrow(button, Priority.ALWAYS);

		Scene scene = new Scene(vbox, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}