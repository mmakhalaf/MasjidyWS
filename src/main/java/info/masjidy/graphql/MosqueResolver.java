package info.masjidy.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import info.masjidy.responses.MosqueResponse;
import info.masjidy.responses.PrayerTimeListResponse;
import info.masjidy.services.MosqueService;
import info.masjidy.services.PrayerTimeService;
import info.masjidy.utils.PrayerTimeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MosqueResolver implements GraphQLResolver<MosqueResponse> {
    @Autowired
    MosqueService mosqueService;

    @Autowired
    PrayerTimeService prayerTimeService;

    public PrayerTimeListResponse getPrayerTimes(MosqueResponse mosqueResponse) {
        Long id = mosqueResponse.getId();
        return new PrayerTimeListResponse(prayerTimeService.GetPrayerTimesForMosque(mosqueResponse.getId()), id);
    }

    public PrayerTimeListResponse getPrayerTimesInRange(
            MosqueResponse mosqueResponse, Integer year, Integer startMonthDay, Integer endMonthDay) {
        Long id = mosqueResponse.getId();
        return new PrayerTimeListResponse(prayerTimeService.GetPrayerTimesForMosqueRange(id, year, startMonthDay, endMonthDay), id);
    }
}
