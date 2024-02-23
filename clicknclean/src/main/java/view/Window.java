package view;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import controller.AskRegistrationController;
import controller.CleanerRegistrationController;
import controller.ConnectionController;
import controller.OwnerRegistrationController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.shape.Rectangle;


public class Window extends Application {
	private static double xOffset = 0;
	private static double yOffset = 0;
 
	Stage stage;
	
	public Window() {

	}
	
	public void displayConnectionView() {
		
		this.stage.setTitle("Connexion");

		Button newOwner = new Button("S'inscrire en tant que Demandeur");
		Button newCleaner = new Button("S'inscrire en tant que Cleaner");
		Button ConnectButton = new Button("Se connecter");
		Label loginLabel = new Label("E-mail :");
		Label passwordLabel = new Label("Mot de passe :");

		TextField loginInputField = new TextField();
		PasswordField passwordInputField = new PasswordField();
		
		Window window = this;

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				new ConnectionController(loginInputField.getText(), passwordInputField.getText(), window);
			}
		};

		ConnectButton.setOnAction(event);
		
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
		vbox.getChildren().add(ConnectButton);
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
		ConnectButton.setMaxWidth(Double.MAX_VALUE);
		newOwner.setMaxWidth(Double.MAX_VALUE);
		newCleaner.setMaxWidth(Double.MAX_VALUE);
		VBox.setVgrow(loginLabel, Priority.ALWAYS);
		VBox.setVgrow(passwordLabel, Priority.ALWAYS);
		VBox.setVgrow(ConnectButton, Priority.ALWAYS);
		VBox.setVgrow(newOwner, Priority.ALWAYS);
		VBox.setVgrow(newCleaner, Priority.ALWAYS);

		Scene scene = new Scene(scrollPane, 800, 600); //scroll
		scene.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
		this.stage.setScene(scene);
		this.stage.show();
	}
	
	public void displayOwnerRegistration() {
		this.stage.setTitle("Inscription");
		
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


		TextField nameInputField = new TextField();
		TextField surnameInputField = new TextField();
		TextField emailInputField = new TextField();
		PasswordField passwordInputField = new PasswordField();
		PasswordField confirmpasswordInputField = new PasswordField();
		TextField phoneInputField = new TextField();
		DatePicker birthDateInputField = new DatePicker();
		ChoiceBox<String> ownerMotivationChoiceBox = new ChoiceBox<>();

		ObservableList<String> options = FXCollections.observableArrayList(
                "Résidence principale", "Courte durée", "Etat des lieux"
		);
		ownerMotivationChoiceBox.setItems(options);

		
		Window window = this;
		
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
				new OwnerRegistrationController(
						nameInputField.getText(), 
						surnameInputField.getText(),
						emailInputField.getText(), 
						passwordInputField.getText(),
						confirmpasswordInputField.getText(),
						phoneInputField.getText(),
						birthDateInputField.getValue(), 
						ownerMotivationChoiceBox.getValue(),
						window
						);
			}
		};

		registerButton.setOnAction(event);

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
		this.stage.setScene(scene);
		this.stage.show();
	}
	
	String photo;
	String idPhoto;
	public void displayCleanerRegistration() {
		this.stage.setTitle("Inscription");
		
		
	
		
		Label title = new Label("S'inscire en tant que Cleaner :");
		Label nameLabel = new Label("Nom :");
		Label surnameLabel = new Label("Prénom :");
		Label emailLabel = new Label("Mail :");
		Label passwordLabel = new Label("Mot de passe :");
		Label confirmpasswordLabel = new Label("Confirmer le mot de passe :");
		Label phoneLabel = new Label("Téléphone :");
		Label birthDateLabel = new Label("Date de naissance :");
		Label addressLabel = new Label("Adresse :");
		Label kmLabel = new Label("Rayon de recherche en km souhaité :");
		Label hourlyRateLabel = new Label("Rémunération par heure :");
		Label biographyLabel = new Label("Biographie :");
		Label motivationLabel = new Label("Votre motivation :");
		Label experienceLabel = new Label("Votre expérience :");
		Label photoLabel = new Label("Votre photo :");
		Label idPhotoLabel = new Label("Photo de votre carte d'identité :");
		
		TextField nameInputField = new TextField();
		TextField surnameInputField = new TextField();
		TextField emailInputField = new TextField();
		PasswordField passwordInputField = new PasswordField();
		PasswordField confirmpasswordInputField = new PasswordField();
		TextField phoneInputField = new TextField();
		DatePicker birthDateInputField = new DatePicker();
		TextField addressInputField = new TextField();
		TextField kmInputField = new TextField();
		TextField hourlyRateInputField = new TextField();
		TextField biographyInputField = new TextField();
		TextField motivationInputField = new TextField();
		TextField experienceInputField = new TextField();
		FileChooser photoInputField = new FileChooser();
		FileChooser idPhotoInputField = new FileChooser();
		
		
		Button registerButton = new Button("Inscription");
		Button registerPhoto = new Button("Parcourir");
		Button registerIdPhoto = new Button("Parcourir");
       
		
		Window window = this;
		
		
		
		
		EventHandler<ActionEvent> eventphoto = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				photo = photoInputField.showOpenDialog(window.stage).toString();
				
			}
		};
		registerPhoto.setOnAction(eventphoto);
		
		
		EventHandler<ActionEvent> eventIdPhoto = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				idPhoto= idPhotoInputField.showOpenDialog(window.stage).toString();
			}
		};
		registerIdPhoto.setOnAction(eventIdPhoto);
		
		
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				
				new CleanerRegistrationController(
						nameInputField.getText(), 
						surnameInputField.getText(),
						emailInputField.getText(), 
						passwordInputField.getText(),
						confirmpasswordInputField.getText(),
						phoneInputField.getText(),
						birthDateInputField.getValue(), 
						addressInputField.getText(),
						kmInputField.getText(),
						hourlyRateInputField.getText(),
						biographyInputField.getText(),
						motivationInputField.getText(),
						experienceInputField.getText(),
						photo,
						idPhoto,
						window
						);
			}
		};

		registerButton.setOnAction(event);
		
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
		vbox.getChildren().add(addressLabel);
		vbox.getChildren().add(addressInputField);
		
		vbox.getChildren().add(kmLabel);
		vbox.getChildren().add(kmInputField);
		vbox.getChildren().add(hourlyRateLabel);
		vbox.getChildren().add(hourlyRateInputField);
		vbox.getChildren().add(biographyLabel);
		vbox.getChildren().add(biographyInputField);
		vbox.getChildren().add(motivationLabel);
		vbox.getChildren().add(motivationInputField);
		vbox.getChildren().add(experienceLabel);
		vbox.getChildren().add(experienceInputField);
		vbox.getChildren().add(photoLabel);
		vbox.getChildren().add(registerPhoto);
		vbox.getChildren().add(idPhotoLabel);
		vbox.getChildren().add(registerIdPhoto);
		
		vbox.getChildren().add(registerButton);
		
		
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
		this.stage.setScene(scene);
		this.stage.show();
		
	}
	
	public void displayWelcomeAdmin() {
		
	}
	
	public void displayWelcomeCleaner(Cleaner cleaner) {
		Button registerProfil = new Button("Profil");
		Button registerNotifications = new Button("Notification");
		Button registerMessages = new Button("Messages");
		Button retour = new Button("Retour");
		
		Mission
		
		Planning
		
		Window window = this;
		
		VBox vbox = new VBox();
		vbox.getChildren().add(registerProfil);
		vbox.getChildren().add(registerNotifications);
		vbox.getChildren().add(registerMessages);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(vbox);
		
		scrollPane.setPannable(true);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		
		Scene scene = new Scene(scrollPane, 800, 600);
		scene.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
		this.stage.setScene(scene);
		this.stage.show();
	}

	public void displayWelcomeOwner() {
	
	}

	public void run() {
		launch();
	}

	public ScrollPane initStage(Stage stage) {
		// stage.initStyle(StageStyle.UNDECORATED);

		ScrollPane root = new ScrollPane();
		
		// Setting vertical scroll bar is never displayed.
		root.setVbarPolicy(ScrollBarPolicy.ALWAYS);

		root.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = stage.getX() - event.getScreenX();
				yOffset = stage.getY() - event.getScreenY();
			}
		});
		root.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				stage.setX(event.getScreenX() + xOffset);
				stage.setY(event.getScreenY() + yOffset);
			}
		});

		Scene scene = new Scene(root, 800, 600);

		scene.getStylesheets().add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));

		this.stage.setScene(scene);

		return root;
	}

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		this.stage.setTitle("Hello World!");
		ScrollPane root = this.initStage(stage);

		this.displayConnectionView();

		

		this.stage.show();
		
	}

}