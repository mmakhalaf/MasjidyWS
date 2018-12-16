package info.masjidy.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;

// We would like to have prayer times such that,
// - An entity can represent a particular day in a year (i.e. a normal day)
// - An entity can be overriden by another for that particular year (i.e. Ramadan)
// - An entity not found in the given year
//   . will fall back onto the last known year
//   . will fall back onto the last know day
//
// That is why our search should return all the available entries between the given dates
// The result would be ordered by year descending
// Then, we filter based on the above criteria

@Entity
@Table(name = "prayer_times")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PrayerTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @NotNull
    @OneToOne
    @JoinColumn(name = "mosqueId")
    private Mosque mosque = null;

    @NotNull
    private Integer monthDay = 0;

    @NotNull
    private Integer year = 0;

    @NotNull
    private Integer fajr = 0;

    @NotNull
    private Integer fajrJamaa = 0;

    @NotNull
    private Integer thuhr = 0;

    @NotNull
    private Integer thuhrJamaa = 0;

    @NotNull
    private Integer asr = 0;

    @NotNull
    private Integer asrJamaa = 0;

    @NotNull
    private Integer maghrib = 0;

    @NotNull
    private Integer maghribJamaa = 0;

    @NotNull
    private Integer ishaa = 0;

    @NotNull
    private Integer ishaaJamaa = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mosque getMosque() {
        return mosque;
    }

    public void setMosque(Mosque mosque) {
        this.mosque = mosque;
    }

    public Integer getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(Integer monthDay) {
        this.monthDay = monthDay;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getFajr() {
        return fajr;
    }

    public void setFajr(Integer fajr) {
        this.fajr = fajr;
    }

    public Integer getFajrJamaa() {
        return fajrJamaa;
    }

    public void setFajrJamaa(Integer fajrJamaa) {
        this.fajrJamaa = fajrJamaa;
    }

    public Integer getThuhr() {
        return thuhr;
    }

    public void setThuhr(Integer thuhr) {
        this.thuhr = thuhr;
    }

    public Integer getThuhrJamaa() {
        return thuhrJamaa;
    }

    public void setThuhrJamaa(Integer thuhrJamaa) {
        this.thuhrJamaa = thuhrJamaa;
    }

    public Integer getAsr() {
        return asr;
    }

    public void setAsr(Integer asr) {
        this.asr = asr;
    }

    public Integer getAsrJamaa() {
        return asrJamaa;
    }

    public void setAsrJamaa(Integer asrJamaa) {
        this.asrJamaa = asrJamaa;
    }

    public Integer getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(Integer maghrib) {
        this.maghrib = maghrib;
    }

    public Integer getMaghribJamaa() {
        return maghribJamaa;
    }

    public void setMaghribJamaa(Integer maghribJamaa) {
        this.maghribJamaa = maghribJamaa;
    }

    public Integer getIshaa() {
        return ishaa;
    }

    public void setIshaa(Integer ishaa) {
        this.ishaa = ishaa;
    }

    public Integer getIshaaJamaa() {
        return ishaaJamaa;
    }

    public void setIshaaJamaa(Integer ishaaJamaa) {
        this.ishaaJamaa = ishaaJamaa;
    }
}
