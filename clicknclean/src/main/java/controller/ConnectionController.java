package controller;


import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;
import javafx.util.Pair;
import model.UserStatus;
import tools.Db;
import view.Window;

public class ConnectionController {



	public ConnectionController(String login, String psw, Window window) throws SQLException, InterruptedException, ExecutionException, Exception {
		Db db = new Db();
		if (login.isEmpty() || psw.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Champs non remplis !");
		} else {
			Pair<Integer, UserStatus> pair = db.DAOReadUser(login, psw);
			JOptionPane.showMessageDialog(null, "Connexion réussie");

			switch (pair.getValue()) {
			case ADMIN :
				window.displayWelcomeAdmin();
				db.DAOReadAdmin(pair.getKey());
				break;
			case CLEANER :
				window.displayWelcomeCleaner();
				db.DAOReadCleaner(pair.getKey());
				break;
			case OWNER :
				window.displayWelcomeOwner();
				db.DAOReadOwner(pair.getKey());
				break;
			}
			JOptionPane.showMessageDialog(null, "Email ou mot de passe incorrect !");


		}
	}
}










