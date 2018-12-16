package info.masjidy.responses;

import info.masjidy.models.Mosque;
import info.masjidy.utils.MosqueList;

import java.util.ArrayList;
import java.util.List;

public class MosqueListResponse {

    private List<MosqueResponse> mosques = new ArrayList<>();

    public MosqueListResponse(MosqueList mosqueList) {
        if (mosqueList != null) {
            for (Mosque m : mosqueList) {
                mosques.add(new MosqueResponse(m));
            }
        }
    }

    public List<MosqueResponse> getMosques() {
        return mosques;
    }

    public void setMosques(List<MosqueResponse> mosques) {
        this.mosques = mosques;
    }
}
