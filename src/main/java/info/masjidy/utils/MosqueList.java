package info.masjidy.utils;

import info.masjidy.models.Mosque;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class MosqueList implements Iterable<Mosque> {

    private List<Mosque> mosqueList = new ArrayList<>();

    public MosqueList() {
    }

    public MosqueList(List<Mosque> mosqueList) {
        this.mosqueList = mosqueList;
    }

    public boolean AddMosque(Mosque mosque) {
        if (HasMosque(mosque.getId()))
            return false;

        try {
            return mosqueList.add(mosque);
        } catch (Exception e) {
            return false;
        }
    }

    public Mosque FindMosque(Long id) {
        for (Mosque m : mosqueList) {
            if (m.getId() == id)
                return m;
        }
        return null;
    }

    public boolean HasMosque(Long id) {
        return FindMosque(id) != null;
    }

    public int size() {
        return mosqueList.size();
    }

    public boolean isEmpty() {
        return mosqueList.isEmpty();
    }

    public Stream<Mosque> stream() {
        return mosqueList.stream();
    }

    /**
     * Only keep the mosques in this list which have the given Id
     */
    public void FilterOnMosqueId(Set<Long> mosqueIdList) {
        mosqueList.removeIf(m -> !mosqueIdList.contains(m.getId()));
    }

    @Override
    public Iterator<Mosque> iterator() {
        return mosqueList.iterator();
    }

    @Override
    public void forEach(Consumer<? super Mosque> action) {
        mosqueList.forEach(action);
    }

    @Override
    public Spliterator<Mosque> spliterator() {
        return mosqueList.spliterator();
    }

    public List<Mosque> getMosqueList() {
        return mosqueList;
    }

    public void setMosqueList(List<Mosque> mosqueList) {
        this.mosqueList = mosqueList;
    }
}
