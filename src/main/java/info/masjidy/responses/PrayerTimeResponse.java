package info.masjidy.responses;

import info.masjidy.models.PrayerTime;

public class PrayerTimeResponse {

    private long mosqueId = 0;
    private int monthDay = 0;
    private int year = 0;
    private int fajr = 0;
    private int fajrJamaa = 0;
    private int thuhr = 0;
    private int thuhrJamaa = 0;
    private int asr = 0;
    private int asrJamaa = 0;
    private int maghrib = 0;
    private int maghribJamaa = 0;
    private int ishaa = 0;
    private int ishaaJamaa = 0;

    /**
     * Constructor for when we don't have a mosque id
     * Avoid this if you can as it will result in a db hit (unless it was previously used before)
     */
    public PrayerTimeResponse(PrayerTime prayerTime) {
        if (prayerTime != null) {
            this.mosqueId = prayerTime.getMosque().getId();
            CopyFromPrayerTime(prayerTime);
        }
    }

    /**
     * Constructor for when we have the mosque id
     */
    public PrayerTimeResponse(PrayerTime prayerTime, Long mosqueId) {
        this.mosqueId = mosqueId;
        if (prayerTime != null) {
            CopyFromPrayerTime(prayerTime);
        }
    }

    private void CopyFromPrayerTime(PrayerTime prayerTime) {
        monthDay = prayerTime.getMonthDay();
        year = prayerTime.getYear();
        fajr = prayerTime.getFajr();
        fajrJamaa = prayerTime.getFajrJamaa();
        thuhr = prayerTime.getThuhr();
        thuhrJamaa = prayerTime.getThuhrJamaa();
        asr = prayerTime.getAsr();
        asrJamaa = prayerTime.getAsrJamaa();
        maghrib = prayerTime.getMaghrib();
        maghribJamaa = prayerTime.getMaghribJamaa();
        ishaa = prayerTime.getIshaa();
        ishaaJamaa = prayerTime.getIshaaJamaa();
    }

    public long getMosqueId() {
        return mosqueId;
    }

    public void setMosqueId(long mosqueId) {
        this.mosqueId = mosqueId;
    }

    public int getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(int monthDay) {
        this.monthDay = monthDay;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getFajr() {
        return fajr;
    }

    public void setFajr(int fajr) {
        this.fajr = fajr;
    }

    public int getFajrJamaa() {
        return fajrJamaa;
    }

    public void setFajrJamaa(int fajrJamaa) {
        this.fajrJamaa = fajrJamaa;
    }

    public int getThuhr() {
        return thuhr;
    }

    public void setThuhr(int thuhr) {
        this.thuhr = thuhr;
    }

    public int getThuhrJamaa() {
        return thuhrJamaa;
    }

    public void setThuhrJamaa(int thuhrJamaa) {
        this.thuhrJamaa = thuhrJamaa;
    }

    public int getAsr() {
        return asr;
    }

    public void setAsr(int asr) {
        this.asr = asr;
    }

    public int getAsrJamaa() {
        return asrJamaa;
    }

    public void setAsrJamaa(int asrJamaa) {
        this.asrJamaa = asrJamaa;
    }

    public int getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(int maghrib) {
        this.maghrib = maghrib;
    }

    public int getMaghribJamaa() {
        return maghribJamaa;
    }

    public void setMaghribJamaa(int maghribJamaa) {
        this.maghribJamaa = maghribJamaa;
    }

    public int getIshaa() {
        return ishaa;
    }

    public void setIshaa(int ishaa) {
        this.ishaa = ishaa;
    }

    public int getIshaaJamaa() {
        return ishaaJamaa;
    }

    public void setIshaaJamaa(int ishaaJamaa) {
        this.ishaaJamaa = ishaaJamaa;
    }
}
