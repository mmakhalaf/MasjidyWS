package info.masjidy.services;

import info.masjidy.models.Mosque;
import info.masjidy.utils.PrayerTimeList;

import java.util.Set;

public interface PrayerTimeService {
    PrayerTimeList GetPrayerTimesForMosque(Long mosqueId);
    PrayerTimeList GetPrayerTimesForMosqueRange(Long mosqueId, Integer year, Integer startMonthDay, Integer endMonthDay);

    /**
     * Remove mosque Ids that don't have prayer time
     * @return the new list of Ids
     */
    void FilterOnPrayerTime(Set<Long> mosqueIdList);
}
