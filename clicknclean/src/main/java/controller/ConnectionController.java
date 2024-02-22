package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import tools.Db;
import view.Window;

public class ConnectionController {


	public ConnectionController(String login, String psw, Window window) {

		Db db1 = new Db();

		try {
			String strQuery = "SELECT * FROM user WHERE email = '" + login + "' AND password = '" + psw + "';";

			if (login.isEmpty() || psw.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Champs non remplis !");
			}

			else {
				ResultSet rsReader = db1.getStRead().executeQuery(strQuery);

				while (rsReader.next()) {
					JOptionPane.showMessageDialog(null, "Connexion réussie");
					switch (rsReader.getString("status")) {
					case "Admin" :
						window.displayWelcomeAdmin();
						String strQueryAdmin = "SELECT * FROM admin WHERE id_;";
						rsReader = db1.getStRead().executeQuery(strQueryAdmin);
						break;
					case "Cleaner" :
						window.displayWelcomeCleaner();
						String strQueryCleaner = "SELECT * FROM ;";
						rsReader = db1.getStRead().executeQuery(strQueryCleaner);
						break;
					case "Owner" :
						window.displayWelcomeOwner();
						String strQueryOwner = "SELECT * FROM ;";
						rsReader = db1.getStRead().executeQuery(strQueryOwner);
						break;
					}
				}






				JOptionPane.showMessageDialog(null, "Email ou mot de passe incorrect !");
				//ConnectionView c1 = new ConnectionView();
				//c1.setVisible(true);

			}
			//rsReader.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}


	}

}