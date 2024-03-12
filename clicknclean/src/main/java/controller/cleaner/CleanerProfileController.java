package controller.cleaner;

import java.sql.Statement;
import java.time.LocalDate;

import javafx.scene.layout.VBox;
import model.Address;
import model.Cleaner;
import model.CleanerExperience;
import model.UserStatus;
import tools.Db;
import view.Window;
import view.cleaner.CleanerProfile;

public class CleanerProfileController {
    public CleanerProfileController(
        Window window,
        Cleaner cleaner,
        String new_name,
        String new_surname,
        String new_email,
        String new_phoneNbr,
        LocalDate new_birthDate,
        boolean new_sus,
        UserStatus new_status,
        Address new_address,
        int new_kmRange,
        int new_hourlyRate,
        String new_biography,
        String new_motivation,
        CleanerExperience new_experience
    ) {
        Db db = new Db();

        try {

            db.DAOUpdateUser(
                cleaner.getCleanerId(),
                new_name,
                new_surname,
                new_email,
                new_phoneNbr,
                new_birthDate,
                new_sus,
                new_status);

            db.DAOUpdateCleaner(
                cleaner.getCleanerId(),
                new_address,
                new_kmRange,
                new_hourlyRate,
                new_biography,
                new_motivation,
                new_experience);
        } catch (Exception e) {
            System.err.println("[ERROR] An error occured while updating Cleaner: " + e);
            return;
        };


        Cleaner new_cleaner;
        try {
            new_cleaner = db.DAOReadCleaner(cleaner.getCleanerId());
        } catch (Exception e) {
            System.err.println("[ERROR] Could not load cleaner ");
            return;
        }

        window.setScene(new view.cleaner.CleanerProfile(new VBox(), window, new_cleaner));
    }
}
