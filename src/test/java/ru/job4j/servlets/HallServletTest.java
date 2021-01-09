package ru.job4j.servlets;

import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.memory.DBStore;
import ru.job4j.memory.MemoryStore;
import ru.job4j.memory.Store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.*", "javax.management.*"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({DBStore.class, LogManager.class})
public class HallServletTest {

    @Test
    public void whenCallMethodDoGetThenResponseContainsDataJSONTypeWithOnePlaceOccupied() throws IOException {
        Store store = new MemoryStore();

        StringWriter stringWriter = new StringWriter();
        stringWriter.write("");
        PrintWriter writer = new PrintWriter(stringWriter);

        PowerMockito.mockStatic(LogManager.class);
        PowerMockito.mockStatic(DBStore.class);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(DBStore.getInstance()).thenReturn(store);
        when(response.getWriter()).thenReturn(writer);

        new HallServlet().doGet(req, response);

        String string = stringWriter.toString();
        writer.flush();

        String expected = "{\"11\":true,\"12\":false}";
        assertThat(string, is(expected));
    }
}