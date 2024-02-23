package controller;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import model.OwnerMotivation;
import view.Window;
import tools.Db;

public class OwnerRegistrationController {

	public OwnerRegistrationController(String name, String surname, String email, String password, String confirmpassword, String phone, LocalDate birthDate, OwnerMotivation motivation, Window window) {

		Db db = new Db();
	
			
		
			
		if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmpassword.isEmpty() || phone.isEmpty() || birthDate == null || motivation==null) {
			JOptionPane.showMessageDialog(null, "Champs non remplis !");
		} 
		else {
			if (password.equals(confirmpassword) && phone.length()==10) {
				try {
					db.DAOAddOwner(name, password, surname, email, phone, birthDate, false, motivation);
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, "L'inscription a échouée");
				}
				JOptionPane.showMessageDialog(null, "Inscription réussi ! Vous allez être redirigez vers votre page d'acceuil !");
				
				window.displayWelcomeOwner();
			} 
			else {
				JOptionPane.showMessageDialog(null, "Le mot de passe n'est pas le même que la confirmation !");
			}
		}
		//db.close();

	}

}
