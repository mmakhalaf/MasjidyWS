package info.masjidy.controllers;

import info.masjidy.responses.PrayerTimeListResponse;
import info.masjidy.services.PrayerTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/public/prayer_time", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PrayerTimesGet {

    @Autowired
    PrayerTimeService prayerTimeService;

    @GetMapping(value = "/mosque_id")
    PrayerTimeListResponse GetPrayerTimesForMosqueId(@RequestParam Long id)
    {
        return new PrayerTimeListResponse(prayerTimeService.GetPrayerTimesForMosque(id), id);
    }

    @GetMapping(value = "/range")
    PrayerTimeListResponse GetPrayerTimesForMosqueIdRanged(
            @RequestParam Long id,
            @RequestParam Integer year,
            @RequestParam Integer startMonthDay,
            @RequestParam Integer endMonthDay) {
        return new PrayerTimeListResponse(prayerTimeService.GetPrayerTimesForMosqueRange(id, year, startMonthDay, endMonthDay), id);
    }
}
