package ru.clevertec.normalino.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.clevertec.normalino.json.animal.HoneyBadger;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {

    static HoneyBadger honeyBadger;

    @BeforeAll
    static void init() {
        honeyBadger = new HoneyBadger("Sokrushitel", 5);
        honeyBadger.setMom(new HoneyBadger("Mama Sokrushitelya", 9));
        honeyBadger.setDad(new HoneyBadger("Papa Sokrushitelya", 10));

        var rebenok1Sokrushitelya = new HoneyBadger("Rebenok1 Sokrushitelya", 3);
        rebenok1Sokrushitelya.getCubs().add(new HoneyBadger("Rebenok rebenka1 sokrushitelya", 1));
        honeyBadger.getCubs().add(rebenok1Sokrushitelya);
        honeyBadger.getCubs().add(new HoneyBadger("Rebenok2 Sokrushitelya", 2));
        honeyBadger.getCubs().add(new HoneyBadger("Rebenok3 Sokrushitelya", 1));
    }

    @Test
    public void validRaw() throws InvocationTargetException, IllegalAccessException {

        var json = NormalinoJSON.stringify(honeyBadger);

        System.out.println(json);

        assertTrue(isValidJSON(json));
    }

    @Test
    public void validFormatted() throws InvocationTargetException, IllegalAccessException {

        var json = NormalinoJSON.stringify(honeyBadger, true);

        System.out.println(json);

        assertTrue(isValidJSON(json));
    }

    public static boolean isValidJSON(final String json) {
        boolean valid = true;
        var objMapper = new ObjectMapper();
        try {
            objMapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            valid = false;
        }
        return valid;
    }
}
