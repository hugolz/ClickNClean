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
		}else {
				Pair<Integer, UserStatus> pair = db.loginUser(login,psw);
				JOptionPane.showMessageDialog(null, "Connexion réussie");
					
					switch (pair.getValue()) {
					case ADMIN :
						try {
							db.loginAdmin(pair.getKey());
						}
						catch (Exception e){
							JOptionPane.showMessageDialog(null, "Email ou mot de passe incorrect !");
						}
						window.displayWelcomeAdmin();
						break;
					case CLEANER :
						try {
							db.loginCleaner(pair.getKey());
						}
						catch (Exception e){
							JOptionPane.showMessageDialog(null, "Email ou mot de passe incorrect !"+ e);
						}
						window.displayWelcomeCleaner();
						break;
					case OWNER :
						try {
							db.loginOwner(pair.getKey());
						}
						catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Email ou mot de passe incorrect !");
						}
						window.displayWelcomeOwner();
						break;
				}
			}
	 }	
}