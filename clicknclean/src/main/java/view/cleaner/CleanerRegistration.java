package view.cleaner;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

import controller.cleaner.CleanerRegistrationController;
import view.SceneId;
import view.Window;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

public class CleanerRegistration {

	String photo;
	String idPhoto;
	String photoLive;
	String kmstring;
	String hourlyrateString;
	int km;
	int hourlyRate;

	public CleanerRegistration(Window window) {
		System.out.println("CleanerRegistration constructor");

		window.setTitle("Inscription");
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

		Label photoLiveLabel = new Label("Photo de vérification d'Id");

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

		EventHandler<ActionEvent> eventphoto = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				photo = photoInputField.showOpenDialog(window.getStage()).toString();

			}
		};
		registerPhoto.setOnAction(eventphoto);

		EventHandler<ActionEvent> eventIdPhoto = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				idPhoto = idPhotoInputField.showOpenDialog(window.getStage()).toString();
			}
		};
		registerIdPhoto.setOnAction(eventIdPhoto);

		EventHandler<ActionEvent> eventPhotoLive = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				photoLive = photoLiveInputField.showOpenDialog(window.getStage()).toString();
			}
		};
		registerPhotoLive.setOnAction(eventPhotoLive);

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.out.println("Triggered");
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
		window.setScene(scene);
	}
}