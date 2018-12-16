package info.masjidy.services;

import info.masjidy.models.Mosque;
import info.masjidy.utils.MosqueDistPair;
import info.masjidy.utils.MosqueList;

import java.util.List;

public interface MosqueService {
    Mosque GetMosque(Long mosqueId);
    List<MosqueDistPair> FindMosques(Double lat, Double lng, Double radius_km);
    List<MosqueDistPair> FindMosqueWithPrayers(Double lat, Double lng, Double radius_km);
    MosqueList GetAllMosques();
    MosqueList GetAllMosquesWithPrayers();
}
