package ru.clevertec.monkeyjson;

import java.lang.reflect.InvocationTargetException;


public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        var honeyBadger = new ru.clevertec.monkeyjson.HoneyBadger("Sokrushitel", 5);
        honeyBadger.setMom(new ru.clevertec.monkeyjson.HoneyBadger("Mama Sokrushitelya", 9));
        honeyBadger.setDad(new ru.clevertec.monkeyjson.HoneyBadger("Papa Sokrushitelya", 10));

        var rebenok1Sokrushitelya = new HoneyBadger("Rebenok1 Sokrushitelya", 3);
        rebenok1Sokrushitelya.getCubs().add(new HoneyBadger("Rebenok rebenka1 sokrushitelya", 1));
        honeyBadger.getCubs().add(rebenok1Sokrushitelya);
        honeyBadger.getCubs().add(new HoneyBadger("Rebenok2 Sokrushitelya", 2));
        honeyBadger.getCubs().add(new HoneyBadger("Rebenok3 Sokrushitelya", 1));

        System.out.println(MonkeyJSON.toJsonString(honeyBadger, true));
    }
}
