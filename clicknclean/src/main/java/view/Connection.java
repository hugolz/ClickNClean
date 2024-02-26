package view;

import java.io.File;
import controller.AskRegistrationController;
import controller.ConnectionController;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

class Connection {
    public Connection(Window window) {
        System.out.println("Connection constructor");

        window.setTitle("Connexion");

        Button newOwner = new Button("S'inscrire en tant que Demandeur");
        Button newCleaner = new Button("S'inscrire en tant que Cleaner");
        Button connectButton = new Button("Se connecter");
        Label loginLabel = new Label("E-mail :");
        Label passwordLabel = new Label("Mot de passe :");

        TextField loginInputField = new TextField();
        PasswordField passwordInputField = new PasswordField();

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                new ConnectionController(loginInputField.getText(), passwordInputField.getText(), window);
            }
        };

        connectButton.setOnAction(event);

        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                new AskRegistrationController("Owner", window);
            }
        };

        newOwner.setOnAction(event2);

        EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                new AskRegistrationController("Cleaner", window);
            }
        };

        newCleaner.setOnAction(event3);

        VBox vbox = new VBox();
        vbox.getChildren().add(loginLabel);
        vbox.getChildren().add(loginInputField);
        vbox.getChildren().add(passwordLabel);
        vbox.getChildren().add(passwordInputField);
        vbox.getChildren().add(connectButton);
        vbox.getChildren().add(newOwner);
        vbox.getChildren().add(newCleaner);

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(220, 300, 20, 300));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vbox);

        scrollPane.setPannable(true);
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

        loginLabel.setMaxWidth(Double.MAX_VALUE);
        passwordLabel.setMaxWidth(Double.MAX_VALUE);
        connectButton.setMaxWidth(Double.MAX_VALUE);
        newOwner.setMaxWidth(Double.MAX_VALUE);
        newCleaner.setMaxWidth(Double.MAX_VALUE);

        VBox.setVgrow(loginLabel, Priority.ALWAYS);
        VBox.setVgrow(passwordLabel, Priority.ALWAYS);
        VBox.setVgrow(connectButton, Priority.ALWAYS);
        VBox.setVgrow(newOwner, Priority.ALWAYS);
        VBox.setVgrow(newCleaner, Priority.ALWAYS);

        Scene scene = new Scene(scrollPane, 800, 600);
        scene.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
        window.setScene(scene);
    }
}
