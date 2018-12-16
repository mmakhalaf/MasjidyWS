package info.masjidy.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import info.masjidy.responses.MosqueDistListResponse;
import info.masjidy.responses.MosqueListResponse;
import info.masjidy.responses.MosqueResponse;
import info.masjidy.services.MosqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryResolver implements GraphQLQueryResolver {
    @Autowired
    private MosqueService mosqueService;

    public MosqueResponse getMosqueById(Long id) {
        return new MosqueResponse(mosqueService.GetMosque(id));
    }

    public MosqueListResponse getAllMosques(Boolean withPrayersOnly) {
        if (withPrayersOnly != null && withPrayersOnly) {
            return new MosqueListResponse(mosqueService.GetAllMosquesWithPrayers());
        } else {
            return new MosqueListResponse(mosqueService.GetAllMosques());
        }
    }

    public MosqueDistListResponse findMosqueByLocation(Double lat, Double lng, Double radius_km, Boolean withPrayersOnly) {
        if (withPrayersOnly != null && withPrayersOnly) {
            return new MosqueDistListResponse(mosqueService.FindMosqueWithPrayers(lat, lng, radius_km));
        } else {
            return new MosqueDistListResponse(mosqueService.FindMosques(lat, lng, radius_km));
        }
    }
}
