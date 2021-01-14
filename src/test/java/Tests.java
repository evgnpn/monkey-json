import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.clevertec.monkeyjson.HoneyBadger;
import ru.clevertec.monkeyjson.MonkeyJSON;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {

    @Test
    public void valid() throws InvocationTargetException, IllegalAccessException {

        var honeyBadger = new HoneyBadger("Sokrushitel", 5);
        honeyBadger.setMom(new HoneyBadger("Mama Sokrushitelya", 9));
        honeyBadger.setDad(new HoneyBadger("Papa Sokrushitelya", 10));

        var rebenok1Sokrushitelya = new HoneyBadger("Rebenok1 Sokrushitelya", 3);
        rebenok1Sokrushitelya.getCubs().add(new HoneyBadger("Rebenok rebenka1 sokrushitelya", 1));
        honeyBadger.getCubs().add(rebenok1Sokrushitelya);
        honeyBadger.getCubs().add(new HoneyBadger("Rebenok2 Sokrushitelya", 2));
        honeyBadger.getCubs().add(new HoneyBadger("Rebenok3 Sokrushitelya", 1));

        assertTrue(isValidJSON(MonkeyJSON.toJsonString(honeyBadger)));
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
