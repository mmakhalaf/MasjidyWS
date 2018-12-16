package info.masjidy.utils;

import info.masjidy.models.PrayerTime;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

/**
 * Given a list of prayer times and a year and the master list of prayer times,
 * finds the prayer times even if it's not in the same year,
 * the list.
 */
public class PrayerTimeResolver {

    public PrayerTimeResolver() {
    }

    public static PrayerTimeList FilterSortedForYear(PrayerTimeList sortedPrayerTimes, int targetYear) {
        if (sortedPrayerTimes.isEmpty())
            return sortedPrayerTimes;

        // Idea 1:
        // Go through the given list and add them to a map
        // For each new time, we only add to the map if it succeeds the existing one (if any) or it's the same year
        //
        // Idea 2:
        // If the list is sorted, then the entry in the map (from idea 1) is always going to be the last element if
        // it's there. So we can get rid of the map al together.
        //
        // The list should come from the database sorted.

        PrayerTimeList new_prayer_times = new PrayerTimeList(sortedPrayerTimes.size());
        for (PrayerTime listPt : sortedPrayerTimes) {
            int listMonthDay = listPt.getMonthDay();
            int listYear = listPt.getYear();
            PrayerTime foundPt = new_prayer_times.getLast();
            if (foundPt != null && foundPt.getMonthDay() == listMonthDay) {
                // If it already has it, only replace it if the year is greater or it matches the required year
                int foundYear = foundPt.getYear();
                if (listYear == targetYear || (foundYear != targetYear && foundYear < listYear))
                    new_prayer_times.Replace(listPt, new_prayer_times.size() - 1);
            } else {
                // The map doesn't have it, so just add it
                new_prayer_times.AddPrayerTime(listPt);
            }
        }

        return new_prayer_times;
    }
}
