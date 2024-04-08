package view.cleaner;

import view.Window;
import java.io.File;

import controller.cleaner.CleanerProfileController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Cleaner;
import model.CleanerExperience;
import model.UserStatus;

public class CleanerProfile extends Scene {
    public CleanerProfile(VBox container, Window window, Cleaner cleaner) {
        super(container, 800, 600);
        window.setTitle("Profile");
        System.out.println("CleanerProfile constructor");
        Button backButton = new Button("Back");

        // user
        TextField nameEdit = new TextField();
        TextField surnnameEdit = new TextField();
        TextField emailEdit = new TextField();
        // TextField passwordEdit = new TextField();
        TextField phoneEdit = new TextField();

        // cleaner
        // TODO: Address
        TextField kmRangeEdit = new TextField();
        TextField hourlyRateEdit = new TextField();
        TextField biographyEdit = new TextField();
        TextField motivationEdit = new TextField();
        ChoiceBox<CleanerExperience> experienceChoice = new ChoiceBox<CleanerExperience>();

        Button updateButton = new Button("Save");
        Label updateConfirmation = new Label("");

        nameEdit.setText(cleaner.getName());
        surnnameEdit.setText(cleaner.getSurname());
        emailEdit.setText(cleaner.getEmail());
        phoneEdit.setText(cleaner.getPhoneNumber());

        kmRangeEdit.setText(cleaner.getKmRange() + ""/* lmao nice toString */);
        hourlyRateEdit.setText(cleaner.getHourlyRate() + "");
        biographyEdit.setText(cleaner.getBiography());
        motivationEdit.setText(cleaner.getMotivation());
        experienceChoice.setItems(FXCollections.observableArrayList(
                                      CleanerExperience.NONE,
                                      CleanerExperience.LESS_1_YEARS,
                                      CleanerExperience.BETWEEN_1_3,
                                      CleanerExperience.MORE_3_YEARS));
        experienceChoice.setValue(cleaner.getExperience());

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ev) {
                window.setScene(new CleanerMain(new VBox(), window, cleaner));
            }
        });

        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ev) {
                new CleanerProfileController(
                    /* Window */ window,
                    /* Cleaner */ cleaner,
                    /* String */ nameEdit.getText(),
                    /* String */ surnnameEdit.getText(),
                    /* String */ emailEdit.getText(),
                    /* String */ phoneEdit.getText(),
                    /* LocalDate */ cleaner.getBirthDate(),
                    /* boolean */ cleaner.isSuspended(),
                    /* UserStatus */ UserStatus.CLEANER,
                    /* Address */ cleaner.getDepartureAddress(),
                    /* int */ Integer.valueOf(kmRangeEdit.getText()),
                    /* int */ Integer.valueOf(hourlyRateEdit.getText()),
                    /* String */ biographyEdit.getText(),
                    /* String */ motivationEdit.getText(),
                    /* CleanerExperience */ experienceChoice.getValue()
                );
                updateConfirmation.setText("La requete de mise à jour des données a échoué");

            }
        });

        container.getChildren().addAll(backButton,
                                       new HBox(new Label("Nom: "), nameEdit),
                                       new HBox(new Label("email: "), emailEdit),
                                       new HBox(new Label("phone: "), phoneEdit),
                                       new HBox(new Label("kmRange: "), kmRangeEdit),
                                       new HBox(new Label("hourlyRate: "), hourlyRateEdit),
                                       new HBox(new Label("biography: "), biographyEdit),
                                       new HBox(new Label("motivation: "), motivationEdit),
                                       new HBox(new Label("experience: "), experienceChoice),
                                       updateButton, updateConfirmation);

        this.getStylesheets()
        .add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));

    }
}
