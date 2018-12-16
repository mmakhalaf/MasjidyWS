package info.masjidy.services;

import info.masjidy.utils.HaversineDistance;
import info.masjidy.models.Mosque;
import info.masjidy.utils.MosqueDistPair;
import info.masjidy.utils.MosqueFinder;
import info.masjidy.utils.MosqueList;
import info.masjidy.repositories.MosqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MosqueServiceImpl implements MosqueService {

    @Autowired
    private MosqueRepository mosqueRepo;

    @Autowired
    private PrayerTimeService prayerTimeService;

    private MosqueList mosqueCache = new MosqueList();
    private boolean mosqueCacheDirty = true;

    @Override
    @Transactional
    public Mosque GetMosque(Long mosqueId) {
        for (Mosque m : GetAllMosques()) {
            if (m.getId() == mosqueId)
                return m;
        }
        return null;
    }

    @Override
    @Transactional
    public List<MosqueDistPair> FindMosques(Double lat, Double lng, Double radius_km) {
        MosqueFinder finder = new MosqueFinder();
        return finder.FindByLocation(GetAllMosques(), lat, lng, radius_km);
    }

    @Override
    @Transactional
    public List<MosqueDistPair> FindMosqueWithPrayers(Double lat, Double lng, Double radius_km) {
        List<MosqueDistPair> mosques = FindMosques(lat, lng, radius_km);
        if (mosques != null && !mosques.isEmpty()) {
            Set<Long> mosqueIdList = ToMosqueIdList(mosques);
            prayerTimeService.FilterOnPrayerTime(mosqueIdList);
            mosques.removeIf(m -> !mosqueIdList.contains(m.getMosque().getId()));
        }
        return mosques;
    }

    @Override
    @Transactional
    public MosqueList GetAllMosques() {
        return UpdateAndGetCache();
    }

    @Override
    @Transactional
    public MosqueList GetAllMosquesWithPrayers() {
        MosqueList mosques = GetAllMosques();
        if (mosques != null && !mosques.isEmpty()) {
            Set<Long> mosqueIdList = ToMosqueIdList(mosques);
            prayerTimeService.FilterOnPrayerTime(mosqueIdList);
            mosques.FilterOnMosqueId(mosqueIdList);
        }
        return mosques;
    }

    private MosqueList UpdateAndGetCache() {
        if (mosqueCache == null || mosqueCache.size() == 0 || mosqueCacheDirty) {
            mosqueCache = new MosqueList(mosqueRepo.findAll());
            mosqueCacheDirty = false;
        }
        return mosqueCache;
    }

    private static Set<Long> ToMosqueIdList(List<MosqueDistPair> mosqueDistPairs) {
        Set<Long> mosqueIds = new HashSet<>(mosqueDistPairs.size());
        mosqueDistPairs.forEach(m -> mosqueIds.add(m.getMosque().getId()));
        return mosqueIds;
    }

    private static Set<Long> ToMosqueIdList(MosqueList mosqueList) {
        Set<Long> mosqueIdList = new HashSet<>();
        mosqueList.forEach(m -> mosqueIdList.add(m.getId()));
        return mosqueIdList;
    }
}
