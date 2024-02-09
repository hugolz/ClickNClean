package model;

import java.util.ArrayList;

import tools.JavaHttpClient;

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

        // create a request
        var request = HttpRequest.newBuilder(
                          URI.create("https://api-adresse.data.gouv.fr/search/?q=" + address))
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
        // coordinates = new Pair<>(coords.get(0), coords.get(1));

        // System.out.println("Coordinates: " + coordinates.getValue(1) + "," +
        // coordinates.getValue(0));

        this.houseNumber = houseNumber;
        this.label = label.replaceAll(" ", "+");
        this.postCode = postCode;
        this.city = city;
        this.longitude = coordinates.get(1);
        this.latitude = coordinates.get(0);
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

    public double calculateDistance(Address targetA2) {
        final double EARTH_RADIUS = 6371.0;
        Address a1 = this;

        double lat1Rad = Math.toRadians(a1.getLatitude());
        double lat2Rad = Math.toRadians(targetA2.getLatitude());
        double lon1Rad = Math.toRadians(a1.getLongitude());
        double lon2Rad = Math.toRadians(targetA2.getLongitude());

        double x = (lon2Rad - lon1Rad) * Math.cos((lat1Rad + lat2Rad) / 2);
        double y = (lat2Rad - lat1Rad);
        double distance = Math.sqrt(x * x + y * y) * EARTH_RADIUS;

        return distance;
    }

    public static void main(String[] args) {
        try {
            Address ad = new Address("28", "av yves thepot", "29000", "quimper");
            System.out.println(ad.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
