package view;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Window extends Application {
	private static double xOffset = 0;
	private static double yOffset = 0;

	public Window() {

	}

	public void run() {
		launch();
	}

	public StackPane initStage(Stage stage) {
		// stage.initStyle(StageStyle.UNDECORATED);

		StackPane root = new StackPane();

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

		File f = new File("src/main/css/style.css");

		System.out.println(f.getAbsolutePath());

		scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

		stage.setScene(scene);

		return root;
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("Hello World!");
		StackPane root = this.initStage(stage);

		Button btn = new Button();
		btn.setText("Say 'Hello World'");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hello World!");
			}
		});

		root.getChildren().add(btn);

		stage.show();
	}

}