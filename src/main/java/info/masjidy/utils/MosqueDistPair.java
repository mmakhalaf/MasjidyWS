package info.masjidy.utils;

import info.masjidy.models.Mosque;

import java.text.DecimalFormat;

public class MosqueDistPair {
    private Mosque mosque;
    private Double distance;

    public MosqueDistPair(Mosque mosque, Double dist) {
        this.mosque = mosque;
        this.distance = dist;
    }

    public Mosque getMosque() {
        return mosque;
    }

    public void setMosque(Mosque mosque) {
        this.mosque = mosque;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
