package controller.owner;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import javafx.scene.control.ScrollPane;
import model.Owner;
import model.OwnerMotivation;
import model.User;
import view.Window;
import view.owner.OwnerMain;
import tools.Db;

public class OwnerRegistrationController {
	private int currentOwnerId;
	public OwnerRegistrationController(String name, String surname, String email, String rawPassword, String rawConfirmpassword, String phone, LocalDate birthDate, OwnerMotivation motivation, Window window) throws InterruptedException, ExecutionException, Exception {
		Db db = new Db();

		if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || rawPassword.isEmpty() || rawConfirmpassword.isEmpty() || phone.isEmpty() || birthDate == null || motivation == null) {
			JOptionPane.showMessageDialog(null, "Champs non remplis !");
			return;
		}

		if (!rawPassword.equals(rawConfirmpassword)) {
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
			currentOwnerId = db.DAOAddOwner(name, User.sha3256Hashing(rawPassword), surname, email, phone, birthDate, false, motivation);
		}

		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "L'inscription a échoué");
		}

		JOptionPane.showMessageDialog(null, "Inscription réussi ! Vous allez être redirigez vers votre page d'acceuil !");
		Db connection = new Db();
		Owner currentOwner = connection.DAOReadOwner(currentOwnerId);
		window.setScene(new OwnerMain(new ScrollPane(), window, currentOwner));



		//db.close();

	}

	public boolean isEmailAdress(String email) {
		Pattern p = Pattern
		            .compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$");
		Matcher m = p.matcher(email.toUpperCase());
		return m.matches();
	}

}
