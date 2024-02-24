package controller;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;

import model.Address;
import model.UserStatus;
import view.Window;
import tools.Db;

public class CleanerRegistrationController {
	public CleanerRegistrationController(
	    String name,
	    String surname,
	    String email,
	    String password,
	    String confirmpassword,
	    String phone,
	    LocalDate birthDate,
	    String houseNumber,
	    String label,
	    String postCode,
	    String city,
	    int km,
	    int hourlyRate,
	    String biography,
	    String motivation,
	    String experience,
	    String photo,
	    String idPhoto,
	    String photoLive,
	    Window window
	) {

		Db db = new Db();
		Address address;
		try {
			address = new Address(houseNumber, label, postCode, city);
		} catch (Exception e) {
			// TODO: Show the user that there was an error with the address
			return;
		}


		if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmpassword.isEmpty() || phone.isEmpty() || birthDate == null || address == null || km == 0 || hourlyRate == 0 || biography.isEmpty() || motivation.isEmpty() || experience.isEmpty() || photo.isEmpty() || idPhoto.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Champs non remplis !");
			return;
		}
		if (!password.equals(confirmpassword)) {
			// Password doesn't match confirmpassword
			JOptionPane.showMessageDialog(null, "Le mot de passe n'est pas le même que la confirmation !");
			return;
		}

		// Idéalement ces valeurs pourraient être des constantes
		// pour que ce soit plus simple de les adapter à la db
		if (phone.length() != 10) {
			// Wrong phone number format
			return;
		}

		if (postCode.length() != 5) {
			// Incorrect postcode format
			return;
		}

		if (biography.length() > 100) {
			// Biography too large
			return;
		}

		if (motivation.length() < 250) {
			// Too much motivation :p
			return;
		}

		if (experience.length() > 250) {
			return;
		}


		try {
			db.DAOAddCleaner(
			    name,
			    password,
			    surname,
			    email,
			    phone,
			    birthDate,
			    false,
			    address,
			    km,
			    hourlyRate,
			    biography,
			    idPhoto,
			    motivation,
			    experience,
			    false,
			    photo,
			    photoLive);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "L'inscription a échouée");
		}

		JOptionPane.showMessageDialog(null, "Inscription réussi ! Vous allez être dirigé vers votre page d'acceuil, vos accès sont limités en attente de confirmation de votre compte");
		window.displayWelcomeCleaner();

		// db.close();
	}
}
