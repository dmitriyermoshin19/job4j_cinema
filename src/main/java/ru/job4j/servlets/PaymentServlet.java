package ru.job4j.servlets;

import com.google.gson.Gson;
import ru.job4j.memory.DBStore;
import ru.job4j.memory.Store;
import ru.job4j.model.Account;
import ru.job4j.model.Place;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PaymentServlet extends HttpServlet {
    private final Store store = DBStore.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        int row = Integer.parseInt(String.valueOf(id.charAt(0)));
        int place = Integer.parseInt(String.valueOf(id.charAt(1)));
        String name = String.format("Ряд %s, место %s", row, place);
        Place placeForAccount = new Place(name, row, place);
        String fio = req.getParameter("name");
        String phone = req.getParameter("phone");
        Account account = new Account(fio, phone);
        boolean result = store.addVisitorPlace(account, placeForAccount);
        Map<String, String> answer = new HashMap<>();
        if (!result) {
            answer.put("answer", "Place is occupied. Please, try again.");
        } else {
            answer.put("answer", "Reservation successful.");
        }
        String json = new Gson().toJson(answer);
        resp.setContentType("json");
        resp.getWriter().write(json);
    }
}
