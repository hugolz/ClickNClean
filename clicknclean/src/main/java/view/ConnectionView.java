package view;

import java.io.File;
import controller.ConnectionController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConnectionView extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Connexion");

		Button connectButton = new Button("Se connecter");
		Label loginLabel = new Label("E-mail :");
		Label passwordLabel = new Label("Mot de passe :");

		TextField loginInputField = new TextField();
		PasswordField passwordInputField = new PasswordField();

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				new ConnectionController(loginInputField.getText(), passwordInputField.getText());
			}
		};

		connectButton.setOnAction(event);

		VBox vbox = new VBox();
		vbox.getChildren().add(loginLabel);
		vbox.getChildren().add(loginInputField);
		vbox.getChildren().add(passwordLabel);
		vbox.getChildren().add(passwordInputField);
		vbox.getChildren().add(connectButton);

		vbox.setSpacing(10);
		vbox.setPadding(new Insets(220, 300, 20, 300));

		loginLabel.setMaxWidth(Double.MAX_VALUE);
		passwordLabel.setMaxWidth(Double.MAX_VALUE);
		connectButton.setMaxWidth(Double.MAX_VALUE);
		VBox.setVgrow(loginLabel, Priority.ALWAYS);
		VBox.setVgrow(passwordLabel, Priority.ALWAYS);
		VBox.setVgrow(connectButton, Priority.ALWAYS);

		Scene scene = new Scene(vbox, 800, 600);
		scene.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}