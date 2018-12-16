package info.masjidy.utils;

import info.masjidy.models.Mosque;
import info.masjidy.models.PrayerTime;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class PrayerTimeList implements Iterable<PrayerTime> {
    private ArrayList<PrayerTime> prayerTimeList;

    public PrayerTimeList() {
        prayerTimeList = new ArrayList<>();
    }

    public PrayerTimeList(int initialCapacity) {
        prayerTimeList = new ArrayList<>(initialCapacity);
    }

    public PrayerTimeList(Set<PrayerTime> prayerTimes) {
        prayerTimeList = new ArrayList<>();
        if (prayerTimes != null)
            prayerTimeList.addAll(prayerTimes);
    }

    public boolean AddPrayerTime(PrayerTime prayerTime) {
        if (HasPrayerTime(prayerTime))
            return false;

        return prayerTimeList.add(prayerTime);

    }

    public boolean HasPrayerTime(PrayerTime prayerTime) {
        return GetPrayerTime(prayerTime.getId()) != null;
    }

    public PrayerTime GetPrayerTime(Long prayerTimeId) {
        for (PrayerTime pt : prayerTimeList) {
            if (pt.getId().equals(prayerTimeId))
                return pt;
        }

        return null;
    }

    public int size() {
        return prayerTimeList.size();
    }

    public boolean isEmpty() {
        return prayerTimeList.isEmpty();
    }

    public Stream<PrayerTime> stream() {
        return prayerTimeList.stream();
    }

    public PrayerTime getLast() {
        if (prayerTimeList.isEmpty())
            return null;
        return prayerTimeList.get(size() - 1);
    }

    public boolean Replace(PrayerTime prayerTime, int index) {
        if (index >= 0 && index < prayerTimeList.size()) {
            prayerTimeList.set(index, prayerTime);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterator<PrayerTime> iterator() {
        return prayerTimeList.iterator();
    }

    @Override
    public void forEach(Consumer<? super PrayerTime> action) {
        prayerTimeList.forEach(action);
    }

    @Override
    public Spliterator<PrayerTime> spliterator() {
        return prayerTimeList.spliterator();
    }

    public ArrayList<PrayerTime> getPrayerTimeList() {
        return prayerTimeList;
    }

    public void setPrayerTimeList(ArrayList<PrayerTime> prayerTimeList) {
        this.prayerTimeList = prayerTimeList;
    }

}
