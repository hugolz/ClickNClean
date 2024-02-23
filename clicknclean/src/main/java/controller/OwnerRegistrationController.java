package controller;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import view.Window;
import tools.Db;

public class OwnerRegistrationController {

	public OwnerRegistrationController(String name, String surname, String email, String password, String confirmpassword, String phone, LocalDate birthDate, String motivation, Window window) {


		if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmpassword.isEmpty() || phone.isEmpty() || birthDate == null || motivation.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Champs non remplis !");
		} else {
			if (password == confirmpassword) {
				JOptionPane.showMessageDialog(null, "Inscription réussi !");
				
				window.displayWelcomeOwner();
			} else {
				JOptionPane.showMessageDialog(null, "Le mot de passe n'est pas le même que la confirmation !");
			}
		}

	}

}
