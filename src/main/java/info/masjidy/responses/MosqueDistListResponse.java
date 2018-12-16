package info.masjidy.responses;

import info.masjidy.utils.MosqueDistPair;

import java.util.ArrayList;
import java.util.List;

public class MosqueDistListResponse {
    List<MosqueDistResponse> mosques;

    public MosqueDistListResponse(List<MosqueDistPair> mosqueDistPairs) {
        mosques = new ArrayList<>(mosqueDistPairs.size());
        mosqueDistPairs.forEach(m -> mosques.add(new MosqueDistResponse(m)));
    }

    public List<MosqueDistResponse> getMosques() {
        return mosques;
    }

    public void setMosques(List<MosqueDistResponse> mosques) {
        this.mosques = mosques;
    }
}
