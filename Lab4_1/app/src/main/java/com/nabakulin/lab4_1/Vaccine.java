package com.nabakulin.lab4_1;

public class Vaccine {
    private String type;
    private String title;

    public Vaccine(String type, String title) {
        this.type = type;
        this.title = title;
    }

    public Vaccine() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
