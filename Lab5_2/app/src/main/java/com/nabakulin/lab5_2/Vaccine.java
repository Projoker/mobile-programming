package com.nabakulin.lab5_2;

public class Vaccine {
    Integer id = 0;
    String type = "";
    String title = "";

    public Vaccine(Integer id, String type, String name) {
        this.id = id;
        this.type = type;
        this.title = name;
    }

    public Vaccine() {
    }
}
