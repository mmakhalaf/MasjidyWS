package info.masjidy.responses;

import info.masjidy.models.PrayerTime;
import info.masjidy.utils.PrayerTimeList;

import java.util.ArrayList;
import java.util.List;

public class PrayerTimeListResponse {

    List<PrayerTimeResponse> prayerTimes = new ArrayList<>();

    public PrayerTimeListResponse(PrayerTimeList pts) {
        if (pts != null) {
            for (PrayerTime pt : pts) {
                prayerTimes.add(new PrayerTimeResponse(pt));
            }
        }
    }

    /**
     * Constructor to be used when all the prayer times are for one mosque.
     * This should be more performant as it won't involve getting the id from underlying mosque object
     */
    public PrayerTimeListResponse(PrayerTimeList pts, Long mosqueId) {
        if (pts != null) {
            for (PrayerTime pt : pts) {
                prayerTimes.add(new PrayerTimeResponse(pt, mosqueId));
            }
        }
    }

    public List<PrayerTimeResponse> getPrayerTimes() {
        return prayerTimes;
    }

    public void setPrayerTimes(List<PrayerTimeResponse> prayerTimes) {
        this.prayerTimes = prayerTimes;
    }
}
