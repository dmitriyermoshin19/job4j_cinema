package ru.job4j.servlets;

import com.google.gson.Gson;
import ru.job4j.memory.DBStore;
import ru.job4j.memory.Store;
import ru.job4j.model.Place;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class HallServlet extends HttpServlet {
    private final Store store = DBStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Place> allPlaces = store.getPlacesHall();
        Set<Place> occupiedPlaces = store.getOccupiedPlaces();
        HashMap<Integer, Boolean> storeIDOccupiedPlaces = new HashMap<>();
        for (Place place : allPlaces) {
            int i = place.getRow() * 10 + place.getPlace();
            if (occupiedPlaces.contains(place)) {
                storeIDOccupiedPlaces.put(i, true);
            } else {
                storeIDOccupiedPlaces.put(i, false);
            }
        }
        String json = new Gson().toJson(storeIDOccupiedPlaces);
        resp.setContentType("json");
        resp.getWriter().write(json);
    }
}
