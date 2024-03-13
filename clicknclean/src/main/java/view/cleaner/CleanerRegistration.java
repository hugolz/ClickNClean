package view.cleaner;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.swing.plaf.basic.BasicProgressBarUI;

import view.Connection;

import controller.cleaner.CleanerRegistrationController;
import view.SceneId;
import view.Window;

import controller.cleaner.CleanerRegistrationController;
import view.SceneId;
import view.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cleaner;
import model.CleanerExperience;
import model.OwnerMotivation;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

public class CleanerRegistration extends Scene {

	String photo;
	String idPhoto;
	String photoLive;
	String kmstring;
	String hourlyrateString;
	int km;
	int hourlyRate;

	public CleanerRegistration(ScrollPane container, Window window) {
		super(container, 800, 600);

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
		Label hourlyRateLabel = new Label("Rémunération par heure (entre 15€ et 40€) :");
		Label biographyLabel = new Label("Biographie (100 caractères max) :");
		Label motivationLabel = new Label("Votre motivation (250 caractères max) :");
		Label experienceLabel = new Label("Votre expérience (250 caractères max) :");
		Label photoLabel = new Label("Votre photo de profil:");
		Label idPhotoLabel = new Label("Photo de votre carte d'identité :");

		Label photoLiveLabel = new Label("Photo de vérification d'Id");

		TextField nameInputField = new TextField();
		nameInputField.setText("aa");
		TextField surnameInputField = new TextField();
		surnameInputField.setText("aa");
		TextField emailInputField = new TextField();
		emailInputField.setText("aa.aa@aa.aa");
		PasswordField passwordInputField = new PasswordField();
		passwordInputField.setText("ee");
		PasswordField confirmpasswordInputField = new PasswordField();
		confirmpasswordInputField.setText("ee");
		TextField phoneInputField = new TextField();
		phoneInputField.setText("1234567890");
		DatePicker birthDateInputField = new DatePicker();

		TextField houseNumberInputField = new TextField();
		houseNumberInputField.setText("28");
		TextField labelInputField = new TextField();
		labelInputField.setText("av yves thepot");

		TextField postCodeInputField = new TextField();
		postCodeInputField.setText("29000");
		TextField cityInputField = new TextField();
		cityInputField.setText("quimper");
		TextField kmInputField = new TextField();
		kmInputField.setText("5");
		TextField hourlyRateInputField = new TextField();
		hourlyRateInputField.setText("30");
		TextField biographyInputField = new TextField();
		biographyInputField.setText("aa");
		TextField motivationInputField = new TextField();
		motivationInputField.setText("aa");
		ChoiceBox<CleanerExperience> experienceChoiceBox = new ChoiceBox<>();

		FileChooser photoInputField = new FileChooser();
		photoInputField.setInitialDirectory(new File("C:/vfcompat.dll"));
		FileChooser idPhotoInputField = new FileChooser();
		idPhotoInputField.setInitialDirectory(new File("C:/vfcompat.dll"));
		FileChooser photoLiveInputField = new FileChooser();
		photoLiveInputField.setInitialDirectory(new File("C:/vfcompat.dll"));
		Button returnview = new Button("Retour");

		Button registerButton = new Button("Inscription");
		Button registerPhoto = new Button("Parcourir");
		Button registerIdPhoto = new Button("Parcourir");
		Button registerPhotoLive = new Button("Parcourir");

		experienceChoiceBox.setItems(FXCollections.observableArrayList(
		                                 CleanerExperience.NONE, CleanerExperience.LESS_1_YEARS, CleanerExperience.BETWEEN_1_3,
		                                 CleanerExperience.MORE_3_YEARS));

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
				    Integer.valueOf(kmInputField.getText(), 10),
				    Integer.valueOf(hourlyRateInputField.getText(), 10),
				    biographyInputField.getText(),
				    motivationInputField.getText(),

				    experienceChoiceBox.getValue(),

				    registerPhoto.getText(),
				    registerIdPhoto.getText(),
				    registerIdPhoto.getText(),
				    window);

			}
		};

		registerButton.setOnAction(event);

		EventHandler<ActionEvent> eventReturn = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				// window.displayConnectionView();

				window.setScene(new Connection(new ScrollPane(), window));

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
		vbox.getChildren().add(experienceChoiceBox);
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

		container.setContent(vbox);

		container.setPannable(true);
		container.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		container.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

		nameLabel.setMaxWidth(Double.MAX_VALUE);
		surnameLabel.setMaxWidth(Double.MAX_VALUE);
		registerButton.setMaxWidth(Double.MAX_VALUE);
		VBox.setVgrow(nameLabel, Priority.ALWAYS);
		VBox.setVgrow(surnameLabel, Priority.ALWAYS);
		VBox.setVgrow(registerButton, Priority.ALWAYS);

		this.getStylesheets()
		.add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));
	}
}