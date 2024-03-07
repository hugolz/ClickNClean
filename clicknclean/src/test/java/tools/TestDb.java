package tools;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import model.ActivityType;
import model.Address;
import model.Cleaner;
import model.CleanerExperience;
import model.Owner;
import model.OwnerMotivation;
import model.User;
import model.UserStatus;
import model.Activity;

import java.net.ConnectException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TestDb {
    @Test
    void cleanerRegistration() {
        // Init vars
        String name = "cleaner username";
        String pwd = User.sha3256Hashing("a cool password");
        String surname = "surname";
        String email = "aa.aa@aa.aa";
        String phoneN = "1234567890";
        LocalDate birthDate = LocalDate.now();
        boolean isSuspended = false;
        Address departureAddress;
        try {
            departureAddress = new Address("28", "av yves thepot", "29000", "quimper");
        } catch (Exception e) {
            System.out.println("[ERROR] cleanerRegistration test failed on address constructor");
            return;
        }
        int kmRange = 5;
        int hourlyRate = 10;
        String bio = "a cool bio";
        String photo = "";
        String motivation = "";
        CleanerExperience experience = CleanerExperience.LESS_1_YEARS;
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
                assert cleaner.getName().equals(name);
                assert cleaner.getSurname().equals(surname);
                assert cleaner.getEmail().equals(email);
                assert cleaner.getPhoneNumber().equals(phoneN);
                assert cleaner.getBirthDate().equals(birthDate);
                assert cleaner.isSuspended() == isSuspended;
                assert cleaner.getDepartureAddress().equals(departureAddress);
                assert cleaner.getKmRange() == kmRange;
                assert cleaner.getHourlyRate() == hourlyRate;
                assert cleaner.getBiography().equals(bio);
                System.out.println("[SUCCESS] cleanerRegistration done");
            } catch (Exception e) {
                System.out.println("[ERROR] cleanerRegistration test failed on cleaner read: " + e);
                return;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Test
    void ownerRegistration() {
        // Init vars
        String name = "owner name";
        String pwd = User.sha3256Hashing("}yvhP&k5y?hYY5;U}nuG%Yl5!^~IkM8K%");
        String surname = "a cool surname";
        String email = "aa.aa@aa.aa";
        String phoneN = "1234567890";
        LocalDate birthDate = LocalDate.now();
        boolean isSuspended = false;
        OwnerMotivation serviceType = OwnerMotivation.MAIN_HOME;

        try {
            Db connection = new Db();
            int ownerId = connection.DAOAddOwner(name,
                                                 pwd,
                                                 surname,
                                                 email,
                                                 phoneN,
                                                 birthDate,
                                                 isSuspended,
                                                 serviceType);

            try {
                Owner owner = connection.DAOReadOwner(ownerId);
                assert owner.getName().equals(name);
                assert owner.getSurname().equals(surname);
                assert owner.getEmail().equals(email);
                assert owner.getPhoneNumber().equals(phoneN);
                assert owner.getBirthDate().equals(birthDate);
                assert owner.isSuspended() == isSuspended;
                assert owner.getServiceType().equals(serviceType);
                System.out.println("[SUCCESS] onwerRegistration done");
            } catch (Exception e) {
                System.out.println("[ERROR] ownerRegistration test failed on owner read: " + e);
                return;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Test
    void cleanerLogin() {
        // Init vars
        String name = "cleaner username";
        String pwd = "a cool password";
        String surname = "surname";
        String email = "a_very.original@email.aa";
        String phoneN = "1234567890";
        LocalDate birthDate = LocalDate.now();
        boolean isSuspended = false;
        Address departureAddress;
        try {
            departureAddress = new Address("28", "av yves thepot", "29000", "quimper");
        } catch (Exception e) {
            System.out.println("cleanerRegistration test failed on address constructor");
            return;
        }
        int kmRange = 5;
        int hourlyRate = 10;
        String bio = "a cool bio";
        String photo = "";
        String motivation = "";
        CleanerExperience experience = CleanerExperience.LESS_1_YEARS;
        boolean isConfirmed = false;
        String photoProfile = "";
        String photoLive = "";

        // make sure that there is a cleaner with thoses reds in db

        Db connection = new Db();
        try {
            int cleanerId = connection.DAOAddCleaner(
                                name,
                                User.sha3256Hashing(pwd),
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
        } catch (Exception e) {
            assert 1 == 0; // Idk how to trigger a panic yet
        }

        // let's try to log in this test cleaner

        try {
            Pair<Integer, UserStatus> id_status = connection.DAOReadUser(email, pwd);
            if (!id_status.getValue().equals(UserStatus.CLEANER)) {

                assert 1 == 0 : "what have you done";
            }
            Cleaner cleaner = connection.DAOReadCleaner(id_status.getKey());
            System.out.println("[SUCCESS] Succesfully logged in cleaner");
        } catch (Exception e) {
            System.err.println("[ERROR] Could not login cleaner " + e);
        }
    }

    @Test
    void ownerLogin() {
        // Init vars
        String name = "owner username";
        String pwd = "a cool owner password";
        String surname = "surname";
        String email = "a_very.original@email.aa2";
        String phoneN = "0987654321";
        LocalDate birthDate = LocalDate.now();
        boolean isSuspended = false;
        OwnerMotivation motivation = OwnerMotivation.MAIN_HOME;

        // make sure that there is a cleaner with thoses reds in db

        Db connection = new Db();
        try {
            int ownerId = connection.DAOAddOwner(
                              name,
                              User.sha3256Hashing(pwd),
                              surname,
                              email,
                              phoneN,
                              birthDate,
                              isSuspended,
                              motivation);

        } catch (Exception e) {
            assert 1 == 0; // Idk how to trigger a panic yet
        }

        // let's try to log in this test owner

        try {
            Pair<Integer, UserStatus> id_status = connection.DAOReadUser(email, pwd);
            if (id_status.getValue() != UserStatus.OWNER) {
                assert 1 == 0 : "what have you done";
            }
            Owner owner = connection.DAOReadOwner(id_status.getKey());
            System.out.println("[SUCCESS] Succesfully logged in owner");
        } catch (Exception e) {
            System.err.println("[ERROR] Could not login owner");
        }
    }

    @Test
    public void activityReadWrite() {
        Db connection = new Db();

        int cleanerId = 7;

        connection.DAOaddActivity(
            ActivityType.WELCOME_CLEANER,
            cleanerId,
            null,
            cleanerId,
            null,
            null,
            null);

        try {
            ArrayList<Activity> activities = connection.DAOReadActivities(cleanerId);
            System.out.println("[SUCCESS] Read " + activities.size() + " activities for cleaner with id: " + cleanerId);

        } catch (Exception e) {

            System.err.println("[ERROR] Failled to fetch activities for id: " + cleanerId + " due to: " + e);

        }

    }
}