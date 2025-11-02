package com.resqher.safety.models;

public class Helpline {
    private String name;
    private String number;
    private String description;
    private String category;

    public Helpline(String name, String number, String description, String category) {
        this.name = name;
        this.number = number;
        this.description = description;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
