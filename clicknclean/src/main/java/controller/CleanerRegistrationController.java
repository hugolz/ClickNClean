package controller;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;

import model.Address;
import model.UserStatus;
import view.Window;
import tools.Db;

public class CleanerRegistrationController {

	public CleanerRegistrationController(String name,
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
	                                     Window window) {
		Db db = new Db();
		Address address;
		try {
			address = new Address(houseNumber, label, postCode, city);
		} catch (Exception e) {
			System.out.println("Could not fetch address information from given infos: " + houseNumber + " " + label
			                   + " " + postCode + "  " + city);
			return;
		}

		if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()
		        || phone.isEmpty() || birthDate == null || address == null || km == 0 || hourlyRate == 0
		        || biography.isEmpty() || motivation.isEmpty() || experience.isEmpty() || photo.isEmpty()
		        || idPhoto.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Champs non remplis !");
		} else {
			if (password == confirmpassword && phone.length() == 10) {
				JOptionPane.showMessageDialog(null,
				                              "Inscription réussi ! Vous allez être dirigé vers votre page d'acceuil, vos accès sont limités en attente de confirmation de votre compte");
				window.displayWelcomeCleaner();

				try {
					db.DAOAddCleaner(name, password, surname, email, phone, birthDate, false, address, km, hourlyRate,
					                 biography, idPhoto, motivation, experience, false, photo, photoLive);

				} catch (Exception e) {
					// (String name, String pwd, String surname, String email, String phoneN,
					// LocalDate birthDate, boolean isSuspended, UserStatus status, Address
					// departureAddress, int kmRange, int hourlyRate, String bio, String photo,
					// String motivation, String experience, boolean isConfirmed, String
					// photoProfile, String photoLive)
				}
			} else {
				JOptionPane.showMessageDialog(null, "Le mot de passe n'est pas le même que la confirmation !");
			}
		}

	}
}
