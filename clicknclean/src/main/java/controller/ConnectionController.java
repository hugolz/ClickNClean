package controller;

import javax.swing.JOptionPane;

import javafx.scene.control.ScrollPane;
import javafx.util.Pair;

import model.Cleaner;
import model.Owner;
import model.Admin;

import model.UserStatus;
import tools.Db;
import view.Window;
import view.admin.AdminMain;
import view.cleaner.CleanerWelcome;
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

			case ADMIN :
				Admin admin = db.DAOReadAdmin(user.getKey());
				System.out.println("okkkkkkkk");
				window.setScene(new AdminMain(new ScrollPane(), window, admin));
				break;
			case CLEANER :
				Cleaner cleaner = db.DAOReadCleaner(user.getKey());
				window.setScene(new CleanerWelcome(new ScrollPane(), window, cleaner));
				break;
			case OWNER :
				System.out.println("okkkkkkkk");
				Owner owner = db.DAOReadOwner(user.getKey());
				window.setScene(new OwnerMain(new ScrollPane(), window, owner));

				break;
			}
			JOptionPane.showMessageDialog(null, "Connexion réussie");
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Connexion échouée !");
		}


	}
}