package info.masjidy.responses;

import info.masjidy.models.Mosque;
import info.masjidy.utils.MosqueDistPair;

public class MosqueDistResponse {
    private MosqueResponse mosque;
    private Double distance;
    public MosqueDistResponse(MosqueDistPair mosqueDistPair) {
        mosque = new MosqueResponse(mosqueDistPair.getMosque());
        distance = mosqueDistPair.getDistance();
    }

    public MosqueResponse getMosque() {
        return mosque;
    }

    public void setMosque(MosqueResponse mosque) {
        this.mosque = mosque;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
