package controller.owner;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import javafx.scene.control.ScrollPane;
import model.OwnerMotivation;
import view.SceneId;
import view.Window;
import view.owner.OwnerWelcome;
import tools.Db;

public class OwnerRegistrationController {

	public OwnerRegistrationController(String name, String surname, String email, String password, String confirmpassword, String phone, LocalDate birthDate, OwnerMotivation motivation, Window window) {
		Db db = new Db();

		if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmpassword.isEmpty() || phone.isEmpty() || birthDate == null || motivation == null) {
			JOptionPane.showMessageDialog(null, "Champs non remplis !");
			return;
		}

		if (!password.equals(confirmpassword)) {
			// Password doesn't match confirmpassword
			JOptionPane.showMessageDialog(null, "Le mot de passe n'est pas le même que la confirmation !");
			return;
		}

		if (phone.length() != 10) {
			// Wrong phone number format
			JOptionPane.showMessageDialog(null, "Numéro de téléphone invalide !");
			return;
		}

		if (isEmailAdress(email) == false) {
			//not good email format
			JOptionPane.showMessageDialog(null, "Mauvais format d'email !");
			return;

		}


		try {
			db.DAOAddOwner(name, password, surname, email, phone, birthDate, false, motivation);
		}

		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "L'inscription a échoué");
		}

		JOptionPane.showMessageDialog(null, "Inscription réussi ! Vous allez être redirigez vers votre page d'acceuil !");

		window.setScene(new OwnerWelcome(new ScrollPane(), window));


		//db.close();

	}

	public boolean isEmailAdress(String email) {
		Pattern p = Pattern
		            .compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");
		Matcher m = p.matcher(email.toUpperCase());
		return m.matches();
	}

}
