package model;

import java.time.LocalDate;
import java.util.ArrayList;

import tools.Db;



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
    private boolean confirmed;
    private ArrayList<Integer> reviews;
    private UserStatus status;
    private Planning planning;

    // Creates a Cleaner object from loaded data
    public Cleaner(
        int cleanerId,
        Address departureAddress,
        int kmRange,
        int hourlyRate,
        String biography,
        String profilePhoto,
        String idPhoto,
        String motivation,
        String experience,
        boolean confirmed,
        String name,
        String pwd,
        String surname,
        String email,
        String phoneNumber,
        LocalDate birthLocalDate,
        boolean suspended,
        UserStatus status,
        ArrayList<Integer> reviews,
        Planning planning
    ) {

        super(name, pwd, surname, email, phoneNumber, birthLocalDate, suspended, status);

        this.cleanerId = cleanerId;
        this.departureAddress = departureAddress;
        this.kmRange = kmRange;
        this.hourlyRate = hourlyRate;
        this.biography = biography;
        this.idPhoto = idPhoto;
        this.profilePhoto = profilePhoto;
        this.motivation = motivation;
        this.experience = experience;
        this.confirmed = confirmed;
        this.reviews = reviews;
        this.planning = planning;
    }

    // Creates a basic Cleaner
    public Cleaner(
        
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
        UserStatus status
    ) {

        super(name, pwd, surname, email, phoneNumber, birthLocalDate, accountLocalDate, suspended, UserStatus.CLEANER);


        Db connection = new Db();
        this.departureAddress = departureAddress;
        this.kmRange = kmRange;
        this.hourlyRate = hourlyRate;
        this.biography = biography;
        this.idPhoto = "idPhoto";
        this.profilePhoto = "profilePhoto";
        this.motivation = motivation;
        this.experience = experience;
        this.confirmed = false;
        this.status = UserStatus.CLEANER;
        this.reviews = new ArrayList<Integer>();
        
        connection.DAOAddCleaner(this);
    }

    public int getCleanerId() {
        return cleanerId;
    }

    public void setCleanerId(int cleanerId) {
        this.cleanerId = cleanerId;
    }

    public Address getDepartureAddress() {
        return this.departureAddress;
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
        return this.confirmed;
    }

    public void setConfirmedId(boolean confirmed) {
        this.confirmed = confirmed;
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

    public UserStatus getStatus() {
        return this.status;
    }


}