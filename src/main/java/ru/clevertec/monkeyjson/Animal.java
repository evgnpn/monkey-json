package ru.clevertec.monkeyjson;

import java.util.ArrayList;
import java.util.List;

public abstract class Animal {

    private String kind;
    private String name;
    private int age;
    private Animal mom;
    private Animal dad;
    private List<Animal> cubs;

    protected Animal(String name, int age, String kind) {
        this.name = name;
        this.age = age;
        setKind(kind);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getKind() {
        return kind;
    }

    private void setKind(String kind) {
        this.kind = kind;
    }

    public Animal getMom() {
        return mom;
    }

    public void setMom(Animal mom) {
        this.mom = mom;
    }

    public Animal getDad() {
        return dad;
    }

    public void setDad(Animal dad) {
        this.dad = dad;
    }

    public List<Animal> getCubs() {
        if (cubs == null) {
            cubs = new ArrayList<>();
        }
        return cubs;
    }
}
