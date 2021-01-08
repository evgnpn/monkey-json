import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.clevertec.jsonstringbuilder.HoneyBadger;
import ru.clevertec.jsonstringbuilder.Main;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {

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

    @Test
    public void valid() {

        var honeyBadger = new HoneyBadger("Sokrushitel", 5);
        honeyBadger.setMom(new HoneyBadger("Mama Sokrushitelya", 9));
        honeyBadger.setDad(new HoneyBadger("Papa Sokrushitelya", 10));

        var rebenok1Sokrushitelya = new HoneyBadger("Rebenok1 Sokrushitelya", 3);
        rebenok1Sokrushitelya.getCubs().add(new HoneyBadger("Rebenok rebenka1 sokrushitelya", 1));
        honeyBadger.getCubs().add(rebenok1Sokrushitelya);
        honeyBadger.getCubs().add(new HoneyBadger("Rebenok2 Sokrushitelya", 2));
        honeyBadger.getCubs().add(new HoneyBadger("Rebenok3 Sokrushitelya", 1));

        var jsonString = Main.getHoneyBadgerJSON(honeyBadger);

        assertTrue(isValidJSON(jsonString));
    }
}
