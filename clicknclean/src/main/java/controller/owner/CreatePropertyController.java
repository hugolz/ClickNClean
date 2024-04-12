package controller.owner;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.scene.control.ScrollPane;
import model.ActivityType;
import model.Address;
import model.Cleaner;
import model.Owner;
import tools.Db;
import view.Window;
import view.owner.OwnerProperty;


public class CreatePropertyController {

	public CreatePropertyController( String houseNumber,
	                                 String label,
	                                 String postCode,
	                                 String city,
	                                 int surface,
	                                 String accesCode,
	                                 String keyBoxCode,
	                                 String specialInstruction,
	                                 Window window,
	                                 Owner owner) throws SQLException, Exception {

		Db db = new Db();
		Address address;
		int ownerId = owner.getOwnerID();
		try {
			address = new Address(houseNumber, label, postCode, city);
		} catch (Exception e) {
			// TODO: Show the user that there was an error with the address
			return;
		}

		if (houseNumber.isEmpty() || label.isEmpty() || postCode.isEmpty() || city.isEmpty()
		        || surface == 0) {

			JOptionPane.showMessageDialog(null, "Champs non remplis !");
			return;
		}

		if (postCode.length() != 5) {
			// Incorrect postcode format
			JOptionPane.showMessageDialog(null, "Code postale invalide !");
			return;
		}

		try {
			db.DAOCreateNewProperty(address, surface, accesCode, keyBoxCode, specialInstruction, ownerId);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "L'ajout de propriété a échoué");
			return;
		}
		JOptionPane.showMessageDialog(null, "Ajout réussi ! Vous allez être redirigez vers votre page des propriétés !");
		db.DAOaddActivity(ActivityType.PROPERTY_IS_PUBLISHED, owner.getOwnerID(), null, null, null, null, null);

		window.setScene(new OwnerProperty(new ScrollPane(), window, owner));
		db.disconnect();


	}

}


