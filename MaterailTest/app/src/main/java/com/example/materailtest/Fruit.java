package com.example.materailtest;

public class Fruit {
    private String Fruit_name;
    private int Fruit_id;

    public Fruit(String fruit_name, int fruit_id) {
        Fruit_name = fruit_name;
        Fruit_id = fruit_id;
    }

    public String getFruit_name() {
        return Fruit_name;
    }

    public void setFruit_name(String fruit_name) {
        Fruit_name = fruit_name;
    }

    public int getFruit_id() {
        return Fruit_id;
    }

    public void setFruit_id(int fruit_id) {
        Fruit_id = fruit_id;
    }
}
