package info.masjidy.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
@Table(name = "mosques")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Mosque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @NotBlank
    private String name = "";

    @NotBlank
    private String addressLine1 = "";

    private String addressLine2 = "";
    private String addressLine3 = "";

    @NotBlank
    private String city = "";

    @NotBlank
    private String country = "";

    @NotBlank
    private String postcode = "";

    @NotNull
    private Double latitude = 0.0;

    @NotNull
    private Double longitude = 0.0;

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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mosque(String name, String[] address_lines, String city, String country, String postcode, Double lat, Double lng) {
        this.name = name;
        if (address_lines.length > 0)
            this.addressLine1 = address_lines[0];
        if (address_lines.length > 1)
            this.addressLine2 = address_lines[1];
        if (address_lines.length > 2)
            this.addressLine3 = address_lines[2];
        this.city = city;
        this.country = country;
        this.postcode = postcode;
        this.latitude = lat;
        this.longitude = lng;
    }

    protected Mosque() {

    }

    @Override
    public String toString() {
        return String.format("Mosque: { id: %d, Name: '%s' }", id, name);
    }
}
