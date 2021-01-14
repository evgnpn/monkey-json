package ru.clevertec.monkeyjson;

public class HoneyBadger extends Animal {

    private boolean lovesHoney;

    public HoneyBadger(String name, int age) {
        super(name, age, "HoneyBadger");
    }

    public boolean isLovesHoney() {
        return lovesHoney;
    }

    public void setLovesHoney(boolean lovesHoney) {
        this.lovesHoney = lovesHoney;
    }
}
