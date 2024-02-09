package model;

public class Address {
    private String houseNumber;
    private String label;
    private String postCode;
    private String city;
    private double latitude;
    private double longitude;

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
        Address ad = new Address("28", "av yves thepot", "29000", "quimper");
        System.out.println(ad.toString());
        
    }
    
}
