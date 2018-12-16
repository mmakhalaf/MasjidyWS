package info.masjidy.services;

import info.masjidy.models.Mosque;
import info.masjidy.models.PrayerTime;
import info.masjidy.repositories.PrayerTimeRepository;
import info.masjidy.utils.PrayerTimeList;
import info.masjidy.utils.PrayerTimeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class PrayerTimeServiceImpl implements PrayerTimeService {
    @Autowired
    PrayerTimeRepository prayerTimeRepository;

    @Override
    @Transactional
    public PrayerTimeList GetPrayerTimesForMosque(Long mosqueId) {
        return new PrayerTimeList(prayerTimeRepository.findByMosqueId(mosqueId));
    }

    @Override
    @Transactional
    public PrayerTimeList GetPrayerTimesForMosqueRange(Long mosqueId, Integer year, Integer startMonthDay, Integer endMonthDay) {
        PrayerTimeList total_list =
                new PrayerTimeList(prayerTimeRepository.findByMosqueIdAndMonthDayBetweenOrderByMonthDay(mosqueId, startMonthDay, endMonthDay));
        total_list = PrayerTimeResolver.FilterSortedForYear(total_list, year);
        return total_list;
    }

    @Override
    @Transactional
    public void FilterOnPrayerTime(Set<Long> mosqueIdList) {
        mosqueIdList.removeIf(mosqueId -> prayerTimeRepository.findDistinctTopByMosqueId(mosqueId) == null);
    }
}
