package ru.job4j.memory;

import ru.job4j.model.Account;
import ru.job4j.model.Place;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryStore implements Store {
    private static final MemoryStore INSTANCE = new MemoryStore();
    private final Map<Place, Account> store = new ConcurrentHashMap<>();

    public MemoryStore() {
        store.put(new Place("Ряд 1, место 1", 1, 1), new Account("Sergei", "+375296666666"));
        store.put(new Place("Ряд 1, место 2", 1, 2), new Account("", ""));
    }

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Place> getPlacesHall() {
        return new ArrayList<>(store.keySet());
    }

    @Override
    public Set<Place> getOccupiedPlaces() {
        Set<Place> result = new HashSet<>();
        for (Place place : store.keySet()) {
            if (!store.get(place).getFio().equals("")) {
                result.add(place);
            }
        }
        return result;
    }

    @Override
    public boolean addVisitorPlace(Account account, Place place) {
        boolean result = false;
        if (store.get(place).getFio().equals("")) {
            store.get(place).setFio(account.getFio());
            result = true;
        }
        return result;
    }
}
