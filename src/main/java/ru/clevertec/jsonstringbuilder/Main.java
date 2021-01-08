package ru.clevertec.jsonstringbuilder;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        var honeyBadger = new HoneyBadger("Sokrushitel", 5);
        honeyBadger.setMom(new HoneyBadger("Mama Sokrushitelya", 9));
        honeyBadger.setDad(new HoneyBadger("Papa Sokrushitelya", 10));

        var rebenok1Sokrushitelya = new HoneyBadger("Rebenok1 Sokrushitelya", 3);
        rebenok1Sokrushitelya.getCubs().add(new HoneyBadger("Rebenok rebenka1 sokrushitelya", 1));
        honeyBadger.getCubs().add(rebenok1Sokrushitelya);
        honeyBadger.getCubs().add(new HoneyBadger("Rebenok2 Sokrushitelya", 2));
        honeyBadger.getCubs().add(new HoneyBadger("Rebenok3 Sokrushitelya", 1));

        System.out.println(getHoneyBadgerJSON(honeyBadger));
    }

    protected static void appendObject(StringBuilder sb, HoneyBadger animal, int deep,
                                       boolean isAssignable,
                                       boolean commaInTheEnd) {

        if (animal == null) {
            sb.append("null");
            if (commaInTheEnd) {
                sb.append(',');
            }
            sb.append('\n');
            return;
        }

        if (!isAssignable) {
            appendTabs(sb, deep);
        }

        sb.append('{').append('\n');

        appendKey(sb, "name", deep + 1);
        appendValue(sb, animal.getName(), true, true);

        appendKey(sb, "age", deep + 1);
        appendValue(sb, animal.getAge(), false, true);

        appendKey(sb, "kind", deep + 1);
        appendValue(sb, animal.getKind(), true, true);

        appendKey(sb, "mom", deep + 1);
        appendObject(sb, (HoneyBadger)animal.getMom(), deep + 1, true, true);

        appendKey(sb, "dad", deep + 1);
        appendObject(sb, (HoneyBadger)animal.getDad(), deep + 1, true, true);

        appendKey(sb, "cubs", deep + 1);
        appendArray(sb, animal.getCubs().stream().map(a -> (HoneyBadger)a).collect(Collectors.toList()),
                deep + 1, true);

        appendKey(sb, "lovesHoney", deep + 1);
        appendValue(sb, animal.isLovesHoney(), false, false);

        appendTabs(sb, deep);

        sb.append('}');
        if (commaInTheEnd) {
            sb.append(',');
        }
        sb.append('\n');
    }

    protected static void appendValue(StringBuilder sb, Object value, boolean quoted, boolean commaInTheEnd) {
        if (quoted) {
            sb.append('"').append(value).append('"');
        } else {
            sb.append(value);
        }
        if (commaInTheEnd) {
            sb.append(',');
        }
        sb.append('\n');
    }

    protected static void appendArray(StringBuilder sb, List<HoneyBadger> array, int deep,
                                      boolean commaInTheEnd) {

        if (array == null) {
            sb.append("null");
        } else if (array.isEmpty()) {
            sb.append("[]");
        } else {
            sb.append('[').append('\n');

            for (int i = 0; i < array.size(); i++) {
                appendObject(sb, array.get(i), deep + 1,
                        false, i + 1 < array.size());
            }

            appendTabs(sb, deep);
            sb.append(']');
        }

        if (commaInTheEnd) {
            sb.append(',');
        }

        sb.append('\n');
    }

    protected static void appendTabs(StringBuilder sb, int count) {
        for (int i = 0; i < count; i++) {
            sb.append("    ");
        }
    }

    protected static void appendKey(StringBuilder sb, String key, int deep) {
        appendTabs(sb, deep);
        sb.append('"').append(key).append('"').append(": ");
    }

    public static String getHoneyBadgerJSON(HoneyBadger honeyBadger) {
        var sb = new StringBuilder();
        appendObject(sb, honeyBadger, 0, false, false);
        return sb.toString();
    }
}
