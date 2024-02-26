package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.OwnerMotivation;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;


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
		                                     "Résidence principale", "Courte durée", "Etat des lieux"
		                                 );
		ownerMotivationChoiceBox.setItems(options);


		Window window = this;

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
				    window
				);
			}
		};

		registerButton.setOnAction(event);

		EventHandler<ActionEvent> eventReturn = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				window.displayConnectionView();
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
		this.stage.setScene(scene);
		this.stage.show();
	}

	String photo;
	String idPhoto;
	String photoLive;
	String kmstring;
	String hourlyrateString;
	int km;
	int hourlyRate;



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
		Label houseNumberLabel = new Label("Numéro :");
		Label labelLabel = new Label("Nom de rue :");
		Label postCodeLabel = new Label("Code postal :");
		Label cityLabel = new Label("Ville :");
		Label kmLabel = new Label("Rayon de recherche en km souhaité :");
		Label hourlyRateLabel = new Label("Rémunération par heure :");
		Label biographyLabel = new Label("Biographie (100 caractères max) :");
		Label motivationLabel = new Label("Votre motivation (250 caractères max) :");
		Label experienceLabel = new Label("Votre expérience (250 caractères max) :");
		Label photoLabel = new Label("Votre photo de profil:");
		Label idPhotoLabel = new Label("Photo de votre carte d'identité :");

		Label photoLiveLabel = new Label ("Photo de vérification d'Id");

		TextField nameInputField = new TextField();
		TextField surnameInputField = new TextField();
		TextField emailInputField = new TextField();
		PasswordField passwordInputField = new PasswordField();
		PasswordField confirmpasswordInputField = new PasswordField();
		TextField phoneInputField = new TextField();
		DatePicker birthDateInputField = new DatePicker();
		TextField houseNumberInputField = new TextField();
		TextField labelInputField = new TextField();
		TextField postCodeInputField = new TextField();
		TextField cityInputField = new TextField();
		TextField kmInputField = new TextField();
		TextField hourlyRateInputField = new TextField();
		TextField biographyInputField = new TextField();
		TextField motivationInputField = new TextField();
		TextField experienceInputField = new TextField();
		FileChooser photoInputField = new FileChooser();
		FileChooser idPhotoInputField = new FileChooser();
		FileChooser photoLiveInputField = new FileChooser();
		Button returnview = new Button("Retour");


		Button registerButton = new Button("Inscription");
		Button registerPhoto = new Button("Parcourir");
		Button registerIdPhoto = new Button("Parcourir");
		Button registerPhotoLive = new Button("Parcourir");


		Window window = this;
		EventHandler<ActionEvent> eventphoto = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				photo = photoInputField.showOpenDialog(window.stage).toString();

			}
		};
		registerPhoto.setOnAction(eventphoto);


		EventHandler<ActionEvent> eventIdPhoto = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				idPhoto = idPhotoInputField.showOpenDialog(window.stage).toString();
			}
		};
		registerIdPhoto.setOnAction(eventIdPhoto);


		EventHandler<ActionEvent> eventPhotoLive = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				photoLive = photoLiveInputField.showOpenDialog(window.stage).toString();
			}
		};
		registerPhotoLive.setOnAction(eventPhotoLive);


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
				    houseNumberInputField.getText(),
				    labelInputField.getText(),
				    postCodeInputField.getText(),
				    cityInputField.getText(),
				    km,
				    hourlyRate,
				    biographyInputField.getText(),
				    motivationInputField.getText(),
				    experienceInputField.getText(),
				    photo,
				    idPhoto,
				    photoLive,
				    window
				);
			}
		};

		registerButton.setOnAction(event);


		EventHandler<ActionEvent> eventReturn = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				window.displayConnectionView();
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
		vbox.getChildren().add(addressLabel);
		vbox.getChildren().add(houseNumberLabel);
		vbox.getChildren().add(houseNumberInputField);
		vbox.getChildren().add(labelLabel);
		vbox.getChildren().add(labelInputField);
		vbox.getChildren().add(postCodeLabel);
		vbox.getChildren().add(postCodeInputField);
		vbox.getChildren().add(cityLabel);
		vbox.getChildren().add(cityInputField);
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
		vbox.getChildren().add(photoLiveLabel);
		vbox.getChildren().add(registerPhotoLive);

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
		this.stage.setScene(scene);
		this.stage.show();

	}

	public void displayWelcomeAdmin() {

	}

	public void displayWelcomeCleaner() {

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