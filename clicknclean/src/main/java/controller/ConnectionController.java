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
			}

			else {
				Pair<Integer, UserStatus> pair = db.loginUser(login,psw);
				JOptionPane.showMessageDialog(null, "Connexion réussie");
					
					switch (pair.getValue()) {
					case ADMIN :
						window.displayWelcomeAdmin();
						db.loginAdmin(pair.getKey());
						break;
					case CLEANER :
						window.displayWelcomeCleaner();
						db.loginCleaner(pair.getKey());
						break;
					case OWNER :
						window.displayWelcomeOwner();
						db.loginOwner(pair.getKey());
						break;
					}
			JOptionPane.showMessageDialog(null, "Email ou mot de passe incorrect !");
				
				
			}
	}	
}
					
			
				