package model;

import java.util.ArrayList;

import tools.JavaHttpClient;

public class Address {
    String houseNumber;
    String label;
    String postCode;
    String city;
    String address;
    double latitude;
    double longitude;

    public Address(String houseNumber, String label, String postCode, String city) {
        this.houseNumber = houseNumber;
        this.label = label.replaceAll(" ", "+");
        this.postCode = postCode;
        this.city = city;
       
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getLabel() {
        return label;
    }

    public String getPostCode() {
        return postCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String toString() {
        return this.houseNumber + "+" + this.label + "+" + this.postCode + "+" + this.city;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public static void main(String[] args) {
        Address ad = new Address("28", "av yves thepot", "29000", "quimper");
        System.out.println(ad.toString());
        
    }
    
}
