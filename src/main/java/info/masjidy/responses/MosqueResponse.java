package info.masjidy.responses;

import info.masjidy.models.Mosque;

public class MosqueResponse {

    private long id = 0;
    private String name = "";
    private String addressLine1 = "";
    private String addressLine2 = "";
    private String addressLine3 = "";
    private String city = "";
    private String country = "";
    private String postcode = "";
    private Double latitude = 0.0;
    private Double longitude = 0.0;

    public MosqueResponse(Mosque mosque) {
        if (mosque != null) {
            id = mosque.getId();
            name = mosque.getName();
            addressLine1 = mosque.getAddressLine1();
            addressLine2 = mosque.getAddressLine2();
            addressLine3 = mosque.getAddressLine3();
            city = mosque.getCity();
            country = mosque.getCountry();
            postcode = mosque.getPostcode();
            latitude = mosque.getLatitude();
            longitude = mosque.getLongitude();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
