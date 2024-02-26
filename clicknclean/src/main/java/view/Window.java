package view;

import java.io.File;

import controller.CleanerRegistrationController;
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
import model.Cleaner;
import model.OwnerMotivation;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

public class Window extends Application {
	private static double xOffset = 0;
	private static double yOffset = 0;

	private Stage stage;
	private SceneId currentScene;

	public Window() {

	}

	public Stage getStage() {
		return this.stage;
	}

	public void setTitle(String newTitle) {
		this.stage.setTitle(newTitle);
	}

	public void setScene(Scene s) {
		this.stage.setScene(s);
		this.currentScene = null;
		System.out.println("New set scene");
	}

	public void setScene(SceneId id) {
		if (this.currentScene == id) {
			// TODO: Can this cause problem ? does switching to the same scene be usefull ?
			// like a refresh or something
			System.err.println("Current scene is already " + id);
			return;
		}


		System.out.println("Requested new scene: " + id);
		switch (id) {
		case CONNECTION:
			new Connection(this);
			break;
		case OWNER_REGISTRATION:
			new OwnerRegistration(this);
			break;
		case CLEANER_REGISTRATION:
			new CleanerRegistration(this);
			break;

		case CLEANER_WECLOME:
			new CleanerWelcome(this);
			break;
		}
		this.currentScene = id;

		System.out.println(this.currentScene);

		this.stage.show();
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

		scene.getStylesheets()
		.add("file:///" + new File("src/main/css/style.css").getAbsolutePath().replace("\\", "/"));

		this.stage.setScene(scene);

		return root;
	}

	@Override
	public void start(Stage stage) {
		this.stage = stage;
		this.stage.setTitle("Hello World!");
		ScrollPane root = this.initStage(stage);

		this.setScene(SceneId.CONNECTION);

		// this.stage.show();

	}

}