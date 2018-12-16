package info.masjidy.repositories;

import info.masjidy.models.PrayerTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PrayerTimeRepository extends JpaRepository<PrayerTime, Long> {
    // Find all the prayer times for the given mosque
    Set<PrayerTime> findByMosqueId(Long mosqueId);

    // Find all the prayer times for the given mosque within the given range
    // ordered by date
    Set<PrayerTime> findByMosqueIdAndMonthDayBetweenOrderByMonthDay(Long mosqueId, Integer minMD, Integer maxMD);

    //
    PrayerTime findDistinctTopByMosqueId(Long mosqueId);
}
