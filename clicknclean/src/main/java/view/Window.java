package view;

import java.io.File;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import javafx.application.Application;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.stage.Stage;
import model.Cleaner;
import model.Owner;
import model.OwnerMotivation;

import view.cleaner.CleanerRegistration;
import view.owner.OwnerRegistration;
import view.cleaner.CleanerMain;



import view.owner.OwnerRegistration;
import view.owner.OwnerProfile;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;


public class Window extends Application {
	private static double xOffset = 0;
	private static double yOffset = 0;
	public static Owner currentOwner;

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

	public <T extends Scene> void setScene(T s) {
		System.out.println("New set scene");
		this.stage.setScene(s);
		this.currentScene = null;
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


		this.setScene(new Connection(new ScrollPane(), this));

		this.stage.show();

	}

}