package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import tools.Db;

public class ConnectionController {
	public ConnectionController(String login, String psw) {

		Db db1 = new Db();

		try {
			String strQuery = "SELECT * FROM user WHERE email = '" + login + "' AND password = '" + psw + "';";

			if (login.isEmpty() || psw.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Champs non remplis !");
			}

			else {
				ResultSet rsReader = db1.getStRead().executeQuery(strQuery);

				while (rsReader.next()) {
					JOptionPane.showMessageDialog(null, "Connection réussie");
					switch (rsReader.getString("statut")) {
					case "Admin" :
						// WelcomeAdmin a1 = new WelcomeAdmin();
						// a1.setVisible(true);
						String strQueryAdmin = "SELECT * FROM admin WHERE id_";
						rsReader = db1.getStRead().executeQuery(strQueryAdmin);
						break;
					case "Cleaner" :
						// WelcomeCleaner c1 = new WelcomeCleaner();
						// c1.setVisible(true);
						String strQueryCleaner = "SELECT * FROM ";
						rsReader = db1.getStRead().executeQuery(strQueryCleaner);
						break;
					case "Owner" :
						// WelcomeOwner o1 = new WelcomeOwner();
						// o1.setVisible(true);
						String strQueryOwner = "SELECT * FROM ";
						rsReader = db1.getStRead().executeQuery(strQueryOwner);
						break;
					}
				}

				//tuer la connection

				JOptionPane.showMessageDialog(null, "Login ou mot de passe incorrect !");
				// Connection c1 = new Connection();
				// c1.setVisible(true);
				System.out.println("ce projet est vraiment génial");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}


	}

}
