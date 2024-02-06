package tools;

import java.util.ArrayList;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

import model.Address;

public class JavaHttpClient {
    public static Address createAddress(String houseNumber, String label, String postCode, String city) throws InterruptedException, ExecutionException {
        Address address = new Address(houseNumber, label, postCode, city);
        ArrayList<Double> coordinates = asynchronousRequest(address.toString());

        if (!coordinates.isEmpty()) {
            address.setLatitude(coordinates.get(1));
            address.setLongitude(coordinates.get(0));
        }

        return address;
    }

    private static ArrayList<Double> asynchronousRequest(String address) throws InterruptedException, ExecutionException {
        ArrayList<Double> coordinates = new ArrayList<Double>();
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
        if (response.statusCode() == 200) {
            // Access the first feature's coordinates (assuming APOD class structure)
            coordinates = response.body().get().features.get(0).geometry.coordinates;
            System.out.println("Coordinates: " + coordinates.get(1) + "," + coordinates.get(0));
        } else {
            System.out.println("Error: " + response.statusCode());
        }

        return coordinates;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Address ad = createAddress("28", "av yves thepot", "29000", "quimper");
        System.out.println("Latitude: " + ad.getLatitude() + ", Longitude: " + ad.getLongitude());
    }
}
