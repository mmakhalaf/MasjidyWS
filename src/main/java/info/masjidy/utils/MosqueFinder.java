package info.masjidy.utils;

import info.masjidy.models.Mosque;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MosqueFinder {

    public List<MosqueDistPair> FindByLocation(MosqueList mosqueList, Double lat, Double lng, Double radius_km) {
        // Aggregate a list of all the distances to the point
        // Remove entries less than the distance
        // Sort by distance
        // We do the sorting after filtering to reduce the number of elements we sort
        List<MosqueDistPair> mosqueDistances = GetAllMosqueDistances(mosqueList, lat, lng);
        return mosqueDistances.stream()
                .filter((MosqueDistPair m) -> IsMosqueWithinDistance(m, lat, lng, radius_km))
                .sorted(MosqueFinder::CompareDistance)
                .collect(Collectors.toList());
    }

    private List<MosqueDistPair> GetAllMosqueDistances(MosqueList mosqueList, Double lat, Double lng) {
        List<MosqueDistPair> distList = new ArrayList<>(mosqueList.size());
        mosqueList.forEach(m ->
                distList.add(new MosqueDistPair(m, HaversineDistance.Calculate(m.getLatitude(), m.getLongitude(), lat, lng)))
        );
        return distList;
    }

    /**
     * @return whether the given mosque is less than radius_km from the given location
     */
    private static boolean IsMosqueWithinDistance(MosqueDistPair mosqueDistPair, Double lat, Double lng, Double radius_km) {
        Mosque m = mosqueDistPair.getMosque();
        double dist_km = HaversineDistance.Calculate(m.getLatitude(), m.getLongitude(), lat, lng);
        return dist_km <= radius_km;
    }

    private static int CompareDistance(MosqueDistPair m1, MosqueDistPair m2) {
        return m1.getDistance().compareTo(m2.getDistance());
    }
}
