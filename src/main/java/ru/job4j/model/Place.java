package ru.job4j.model;

import java.util.Objects;

public class Place {
    private final String name;
    private final int row;
    private final int place;

    public Place(String name, int row, int place) {
        this.name = name;
        this.row = row;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public int getRow() {
        return row;
    }

    public int getPlace() {
        return place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Place place1 = (Place) o;
        return row == place1.row
                && place == place1.place
                && Objects.equals(name, place1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, row, place);
    }

    @Override
    public String toString() {
        return String.format(
                "Place: name=%s, row=%s, place=%s", name, row, place
        );
    }
}
