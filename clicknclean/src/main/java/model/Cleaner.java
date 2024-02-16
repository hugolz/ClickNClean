package model;

import java.time.LocalDate;
import java.util.ArrayList;



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
    private ArrayList<String> availableDays;
    private ArrayList<Integer> reviews;
    private int status;
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
        int status,
        ArrayList<Integer> reviews) {

        super(name, pwd, surname, email, phoneNumber, birthLocalDate, accountLocalDate, suspended, status);

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
        this.planning = new Planning(this.cleanerId);
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
        boolean suspended,
        int status
    ) {
        super(name, pwd, surname, email, phoneNumber, birthLocalDate, accountLocalDate, suspended, status);

        this.cleanerId = cleanerId;
        this.departureAddress = departureAddress;
        this.kmRange = kmRange;
        this.hourlyRate = hourlyRate;
        this.biography = biography;
        this.idPhoto = "idPhoto";
        this.profilePhoto = "profilePhoto";
        this.motivation = motivation;
        this.experience = experience;
        this.confirmedId = false;
        this.status = 2;
        this.reviews = new ArrayList<Integer>();
        this.planning = new Planning(cleanerId);
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
        return this.biography;
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
        return this.profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getMotivation() {
        return this.motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getExperience() {
        return this.experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public boolean isConfirmedId() {
        return this.confirmedId;
    }

    public void setConfirmedId(boolean confirmedId) {
        this.confirmedId = confirmedId;
    }

    public ArrayList<String> getAvailableDays() {
        return this.availableDays;
    }

    public void setAvailableDays(ArrayList<String> availableDays) {
        this.availableDays = availableDays;
    }

    public ArrayList<Integer> getReviews() {
        return this.reviews;
    }

    public void setReviews(ArrayList<Integer> reviews) {
        this.reviews = reviews;
    }

    public Planning getPlanningDB() {
        return this.planning;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }

    public int getStatus() {
        return this.status;
    }


}