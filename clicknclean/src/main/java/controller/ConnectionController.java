package controller;

import javax.swing.JOptionPane;

import controller.admin.AdminMainController;
import javafx.scene.control.ScrollPane;
import javafx.util.Pair;

import model.Cleaner;
import model.Owner;
import model.Admin;

import model.UserStatus;
import tools.Db;
import javafx.scene.layout.VBox;
import view.Window;
import view.admin.AdminMain;
import view.cleaner.CleanerMain;
import view.owner.OwnerMain;

public class ConnectionController {
	public ConnectionController(String login, String psw, Window window) {
		Db db = new Db();
		if (login.isEmpty() || psw.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Champs non remplis !");
			return;
		}

		Pair<Integer, UserStatus> user;

		try {
			user = db.DAOReadUser(login, psw);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Email ou mot de passe incorrect !");
			return;
		}



		try {
			switch (user.getValue()) {

			case ADMIN:
				Admin admin = db.DAOReadAdmin(user.getKey());
				// TODO: scene for ADMIN_WELCOME
				new AdminMainController(window, admin, 0, 0, 0, 0, 0, "");
				System.err.println(user.getKey());
				break;

			case CLEANER:
				Cleaner cleaner = db.DAOReadCleaner(user.getKey());

				window.setScene(new CleanerMain (new VBox(), window, cleaner));

				break;
			case OWNER :
				Owner owner = db.DAOReadOwner(user.getKey());

				window.setScene(new OwnerMain(new ScrollPane(), window, owner));
				break;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Internal error: Could not find a given " + user.getValue()

			                              + "for user: " + user.getKey() + "SQL error: " + e);
		}
	}
}
















