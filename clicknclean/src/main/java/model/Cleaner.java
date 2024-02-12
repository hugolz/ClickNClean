package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Cleaner extends User {

    private int cleanerId;
    private Address departureAddress;
    private int kmRange;
    private int hourlyRate;
    private String biography;
    private String idPhoto;
    private String profilePhoto;
    private String motivation;
    private String experience;
    private boolean confirmedId;
    private List<String> availableDays;
    private List<String> reviews;
    private Planning planning;



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
        LocalDate birthLocalDate,
        LocalDate accountLocalDate,
        boolean suspended,
        List<String> reviews) {

        super(name, pwd, surname, email, phoneNumber, birthLocalDate, accountLocalDate, suspended);

        // this.cleanerId = cleanerId;
        // this.departureAddress = departureAddress;
        // this.kmRange = kmRange;
        // this.hourlyRate = hourlyRate;
        // this.planning = new Planning();
        // this.motivation = motivation;
        // this.experience = experience;
        // this.idPhoto = idPhoto;
        // this.profilePhoto = profilePhoto;
        // this.confirmed = confirmed;
        // this.biography = biography;
        // this.reviews = reviews;

        this.cleanerId = cleanerId;
        this.departureAddress = departureAddress;
        this.kmRange = kmRange;
        this.hourlyRate = hourlyRate;
        this.biography = biography;
        this.idPhoto = idPhoto;
        this.profilePhoto = profilePhoto;
        this.motivation = motivation;
        this.experience = experience;
        this.confirmedId = confirmedId;
        this.availableDays = availableDays;
        this.reviews = reviews;
        this.planning = new Planning();
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
        LocalDate birthLocalDate,
        LocalDate accountLocalDate,
        boolean suspended
    ) {
        super(name, pwd, surname, email, phoneNumber, birthLocalDate, accountLocalDate, suspended);

        this.cleanerId = cleanerId;
        this.departureAddress = departureAddress;
        this.kmRange = kmRange;
        this.hourlyRate = hourlyRate;
        this.biography = biography;
        this.idPhoto = idPhoto;
        this.profilePhoto = profilePhoto;
        this.motivation = motivation;
        this.experience = experience;
        this.confirmedId = confirmedId;
        this.availableDays = availableDays;
        this.reviews = new ArrayList();
        this.planning = new Planning();
    }

    public Cleaner(int i, Address address, int j, int k, String string, String string2, String string3, String string4,
                   String string5, boolean b, String string6, String string7, String string8, String string9, int l,
                   LocalDate birth, String string10, boolean c) {
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

    public int getKmRange() {
        return kmRange;
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
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

    public boolean isConfirmedId() {
        return confirmedId;
    }

    public void setConfirmedId(boolean confirmedId) {
        this.confirmedId = confirmedId;
    }

    public List<String> getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(List<String> availableDays) {
        this.availableDays = availableDays;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        this.reviews = reviews;
    }

    public Planning getPlanning() {
        return planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }


}
