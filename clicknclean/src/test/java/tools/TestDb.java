package tools;

import org.junit.jupiter.api.Test;

import model.planning.TimeSlot;
import model.Address;
import model.Cleaner;
import model.planning.Planning;
import tools.Db;
import java.util.ArrayList;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class TestDb {
    @Test
    void cleanerRegistration() {
        // Init vars
        String name = "";
        String pwd = "";
        String surname = "";
        String email = "";
        String phoneN = "";
        LocalDate birthDate = LocalDate.now();
        boolean isSuspended = false;
        Address departureAddress;
        try {
            departureAddress = new Address("28", "av yves thepot", "29000", "quimper");
        } catch (Exception e) {
            System.out.println("cleanerRegistration test failled on address constructor");
            return;
        }
        int kmRange = 5;
        int hourlyRate = 10;
        String bio = "a cool bio";
        String photo = "";
        String motivation = "";
        String experience = "None";
        boolean isConfirmed = false;
        String photoProfile = "";
        String photoLive = "";

        try {
            Db connection = new Db();

            int cleanerId = connection.DAOAddCleaner(
                                name,
                                pwd,
                                surname,
                                email,
                                phoneN,
                                birthDate,
                                isSuspended,
                                departureAddress,
                                kmRange,
                                hourlyRate,
                                bio,
                                photo,
                                motivation,
                                experience,
                                isConfirmed,
                                photoProfile,
                                photoLive);

            try {
                Cleaner cleaner = connection.DAOReadCleaner(cleanerId);
                assert name.equals(name): "Assert failled for name";
                assert pwd.equals(pwd): "Assert failled for pwd";
                assert surname.equals(surname): "Assert failled for surname";
                assert email.equals(email): "Assert failled for email";
                assert phoneN.equals(phoneN): "Assert failled for phoneN";
                assert birthDate.equals(birthDate): "Assert failled for birthDate";
                assert isSuspended == isSuspended: "Assert failled for isSuspended";
                assert departureAddress.equals(departureAddress): "Assert failled for departureAddress";
                assert kmRange == kmRange: "Assert failled for kmRange";
                assert hourlyRate == hourlyRate: "Assert failled for hourlyRate";
                assert bio.equals(bio): "Assert failled for bio";
            } catch (Exception e) {
                System.out.println("cleanerRegistration test failled on cleaner read: " + e);

                return;

            }

        } catch (Exception e) {
            System.err.println(e);
        }

    }
}