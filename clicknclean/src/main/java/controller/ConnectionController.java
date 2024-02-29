package controller;

import javax.swing.JOptionPane;

import javafx.scene.control.ScrollPane;
import javafx.util.Pair;
import model.UserStatus;
import tools.Db;
import view.Window;
import view.cleaner.CleanerWelcome;
import view.owner.OwnerWelcome;
import view.SceneId;

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

			return;
		}

		JOptionPane.showMessageDialog(null, "Connexion réussie");

		try {
			switch (user.getValue()) {

			case ADMIN :
				db.DAOReadAdmin(user.getKey());
				// window.displayWelcomeAdmin();
				// TODO: scene for ADMIN_WELCOME
				//  window.setScene(SceneId.ADMIN_WELCOME);
				break;
			case CLEANER :
				db.DAOReadCleaner(user.getKey());
				// window.displayWelcomeCleaner();
				window.setScene(new CleanerWelcome(new ScrollPane()));
				break;
			case OWNER :
				db.DAOReadOwner(user.getKey());
				// window.displayWelcomeOwner();
				// TODO: scene for OWNER_WELCOME
				window.setScene(new OwnerWelcome(new ScrollPane(), window));
				break;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Email ou mot de passe incorrect !");
		}


	}
}