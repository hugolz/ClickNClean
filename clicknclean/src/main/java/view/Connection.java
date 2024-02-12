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

public class Connection extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Connection");

		Button button = new Button("Log in");
		Label etiquette1 = new Label("Login :");
		Label etiquette2 = new Label("Password :");

		TextField loginField = new TextField();
		PasswordField passwordField = new PasswordField();

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				// new ConnectionController(loginField.getText(), passwordField.getText());
			}
		};

		button.setOnAction(event);

		VBox vbox = new VBox();
		vbox.getChildren().add(etiquette1);
		vbox.getChildren().add(loginField);
		vbox.getChildren().add(etiquette2);
		vbox.getChildren().add(passwordField);
		vbox.getChildren().add(button);

		vbox.setSpacing(10);
		vbox.setPadding(new Insets(220, 300, 20, 300));

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
