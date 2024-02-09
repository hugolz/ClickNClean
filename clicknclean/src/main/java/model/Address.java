package model;

import java.util.ArrayList;

import java.util.ArrayList;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;
import tools.JsonBodyHandler;
import tools.APOD;

import org.javatuples.Pair;
import java.lang.Throwable;

public class Address {
    String houseNumber;
    String label;
    String postCode;
    String city;
    String address;
    double latitude;
    double longitude;

    public Address(String houseNumber, String label, String postCode, String city)
    throws InterruptedException, ExecutionException {
        // create a client
        var client = HttpClient.newHttpClient();

        String query = (houseNumber + "+" + label + "+" + postCode + "+" + city).replaceAll(" ", "+");
        System.out.println("Querying adress: " + query);
        // create a request
        var request = HttpRequest.newBuilder(
                          URI.create("https://api-adresse.data.gouv.fr/search/?q=" + query))
                      .header("accept", "application/json")
                      .build();

        // use the client to send the request
        var responseFuture = client.sendAsync(request, new JsonBodyHandler<>(APOD.class));

        // This blocks until the request is complete
        var response = responseFuture.get();

        if (response.statusCode() != 200) {
            System.out.println("Error: " + response.statusCode());
            throw new ExecutionException("Could not join the api correctly", new Throwable("Salut"));
        }

        // Access the first feature's coordinates (assuming APOD class structure)
        ArrayList<Double> coordinates = response.body().get().features.get(0).geometry.coordinates;

        this.houseNumber = houseNumber;
        this.label = label.replaceAll(" ", "+");
        this.postCode = postCode;
        this.city = city;
        this.longitude = coordinates.get(0);
        this.latitude = coordinates.get(1);
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

    // Result is in meters
    public double calculateDistance(Address target) {
        final double EARTH_RADIUS = 6371.0;
        Address a1 = this;

        double lat1Rad = Math.toRadians(this.getLatitude());
        double lat2Rad = Math.toRadians(target.getLatitude());
        double lon1Rad = Math.toRadians(this.getLongitude());
        double lon2Rad = Math.toRadians(target.getLongitude());

        double x = (lon2Rad - lon1Rad) * Math.cos((lat1Rad + lat2Rad) / 2);
        double y = (lat2Rad - lat1Rad);
        double distance = Math.sqrt(x * x + y * y) * EARTH_RADIUS;

        return distance * 1000;
    }

    public static void main(String[] args) {
        try {
            Address ad1 = new Address("28", "av yves thepot", "29000", "quimper");
            Address ad2 = new Address("1", "Pl. Louis Armand", "29000", "quimper");
            System.out.println("Lat: " + ad1.latitude + ", Lon: " + ad1.longitude);
            System.out.println("Lat: " + ad2.latitude + ", Lon: " + ad2.longitude);
            System.out.println(ad1.calculateDistance(ad2) + " meters");
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }
}
