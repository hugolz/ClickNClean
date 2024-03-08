package controller;

import javafx.scene.control.ScrollPane;
import view.Window;

public class LogoutController {

	public LogoutController(Window window, Integer userId) {
		System.out.println("Logout Controller");
		window.setScene(new view.Connection(new ScrollPane(), window));
	}
}
