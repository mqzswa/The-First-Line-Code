package com.example.recyclerviewtest;

import android.widget.ArrayAdapter;

public class Fruit{

    private String name;
    private int id;

    public Fruit(String name,int id){
        this.name=name;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
