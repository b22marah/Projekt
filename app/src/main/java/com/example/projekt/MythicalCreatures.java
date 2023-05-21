package com.example.projekt;

public class MythicalCreatures {
    private String ID;
    private String name;
    private String type;
    private String company;
    private String location;
    private String category;

    public MythicalCreatures(String ID, String name, String type, String company, String location, String category) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.company = company;
        this.location = location;
        this.category = category;

    }

    @Override
    public String toString() {
        String presentCreatures = "Creatures: " + ID + " Name: " + name + " Element: " + company + " Power: " + location + " Category: " + category + " Creater " + type;
        return presentCreatures;
    }
}