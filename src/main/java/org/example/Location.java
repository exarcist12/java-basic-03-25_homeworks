package org.example;

public enum Location {
    FOREST("Лес"),
    PLAIN("Равнина"),
    SWAMP("Болото");

    public String name;

    Location(String name) {
        this.name = name;
    }
}


