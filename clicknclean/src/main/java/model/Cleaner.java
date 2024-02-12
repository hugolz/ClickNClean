package model;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Cleaner extends User {
    Planning planning;
    int cleanerId;
    Address departureAddress;
    int kmRange;
    int hourlyRate;
    List<String> availableDays;
    String motivation;
    String experience;
    String idPhoto;
    String profilePhoto;
    boolean confirmed;
    String biography;
    List<String> reviews;


    // Creates a Cleaner object from loaded data
    public Cleaner(
        int cleanerId,
        Address departureAddress,
        int kmRange,
        int hourlyRate,
        String biography,
        String idPhoto,
        String profilePhoto,
        String motivation,
        String experience,
        boolean confirmed,
        String name,
        String pwd,
        String surname,
        String email,
        String phoneNumber,
        Date birthDate,
        Date accountDate,
        boolean suspended,
        List<String> reviews) {

        super(name, pwd, surname, email, phoneNumber, birthDate, accountDate, suspended);

        this.cleanerId = cleanerId;
        this.departureAddress = departureAddress;
        this.kmRange = kmRange;
        this.hourlyRate = hourlyRate;
        this.planning = new Planning();
        this.motivation = motivation;
        this.experience = experience;
        this.idPhoto = idPhoto;
        this.profilePhoto = profilePhoto;
        this.confirmed = confirmed;
        this.biography = biography;
        this.reviews = reviews;
    }

    // Creates a basic Cleaner
    public Cleaner(
        int cleanerId,
        Address departureAddress,
        int kmRange,
        int hourlyRate,
        String biography,
        String photo,
        String motivation,
        String experience,
        boolean confirmed,
        String name,
        String pwd,
        String surname,
        String email,
        String phoneNumber,
        Date birthDate,
        Date accountDate,
        boolean suspended
    ) {
        super(name, pwd, surname, email, phoneNumber, birthDate, accountDate, suspended);

        this.cleanerId = cleanerId;
        this.departureAddress = departureAddress;
        this.kmRange = kmRange;
        this.hourlyRate = hourlyRate;
        this.planning = new Planning();
        this.motivation = motivation;
        this.experience = experience;
        this.idPhoto = idPhoto;
        this.profilePhoto = profilePhoto;
        this.confirmed = confirmed;
        this.biography = biography;
        this.reviews = new ArrayList<>();
    }

    public Planning getPlanning() {
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }

    public int getCleanerId() {
        return cleanerId;
    }

    public void setCleanerId(int cleanerId) {
        this.cleanerId = cleanerId;
    }

    public Address getDepartureAddress() {
        return departureAddress;
    }

    public void setDepartureAddress(Address departureAddress) {
        this.departureAddress = departureAddress;
    }

    public void setCleanerID(int cleanerID) {
        this.cleanerID = cleanerID;
    }

    public int getKmRange() {
        return this.kmRange;

    }

    public void setKmRange(int kmRange) {
        this.kmRange = kmRange;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public List<String> getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(List<String> availableDays) {
        this.availableDays = availableDays;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(String idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }
}
