package controller;


import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;
import javafx.util.Pair;
import model.UserStatus;
import tools.Db;
import view.Window;

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
				window.displayWelcomeAdmin();
				break;
			case CLEANER :
				db.DAOReadCleaner(user.getKey());
				window.displayWelcomeCleaner();
				break;
			case OWNER :
				db.DAOReadOwner(user.getKey());
				window.displayWelcomeOwner();
				break;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Email ou mot de passe incorrect !");
		}


	}
}