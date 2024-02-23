package tools;

import org.junit.jupiter.api.Test;

import model.Address;
import model.Cleaner;
import model.Owner;
import model.OwnerMotivation;
import java.time.LocalDateTime;

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
                assert cleaner.getName().equals(name);
                assert cleaner.getPwd().equals(pwd);
                assert cleaner.getSurname().equals(surname);
                assert cleaner.getEmail().equals(email);
                assert cleaner.getPhoneNumber().equals(phoneN);
                assert cleaner.getBirthDate().equals(birthDate);
                assert cleaner.isSuspended() == isSuspended ;
                assert cleaner.getDepartureAddress().equals(departureAddress);
                assert cleaner.getKmRange() == kmRange;
                assert cleaner.getHourlyRate() == hourlyRate;
                assert cleaner.getBiography().equals(bio);
            } catch (Exception e) {
                System.out.println("cleanerRegistration test failled on cleaner read: " + e);
                return;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Test
    void ownerRegistration() {
        // Init vars
        String name = "";
        String pwd = "";
        String surname = "";
        String email = "";
        String phoneN = "";
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
                assert owner.getPwd().equals(pwd);
                assert owner.getSurname().equals(surname);
                assert owner.getEmail().equals(email);
                assert owner.getPhoneNumber().equals(phoneN);
                assert owner.getBirthDate().equals(birthDate);
                assert owner.isSuspended() == isSuspended ;
                assert owner.getServiceType().equals(serviceType);
            } catch (Exception e) {
                System.out.println("ownerRegistration test failled on owner read: " + e);
                return;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}