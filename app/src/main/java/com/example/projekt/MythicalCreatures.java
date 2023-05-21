package com.example.projekt;

public class MythicalCreatures {
    private String ID;
    private String name;
    private String creator;
    private String element;
    private String power;
    private String category;

    public MythicalCreatures(String ID, String name, String creator, String element, String power, String category) {
        this.ID = ID;
        this.name = name;
        this.creator = creator;
        this.element = element;
        this.power = power;
        this.category = category;

    }

    @Override
    public String toString() {
        String presentCreatures = "Creatures: " + ID + " Name: " + name + " Element: " + element + " Power: " + power + " Category: " + category + " Creator " + creator;
        return presentCreatures;
    }
}