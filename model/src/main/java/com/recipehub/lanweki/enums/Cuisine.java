package com.recipehub.lanweki.enums;

import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.Arrays;

public enum Cuisine {
    ITALIAN("italian"),
    GEORGIAN("georgian"),
    CHINESE("chinese"),
    FRENCH("french"),
    JAPANESE("japanese"),
    INDIAN("indian"),
    AMERICAN("american"),
    SPANISH("spanish"),
    GREEK("greek"),
    OTHER("other");

    private final String name;

    Cuisine(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Cuisine fromString(String value) throws HttpMessageNotReadableException {
        return Arrays.stream(Cuisine.values())
                .filter(val -> val.getName().equalsIgnoreCase(value) || val.toString().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}
