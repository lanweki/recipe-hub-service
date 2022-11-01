package com.recipehub.lanweki.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Cuisine fromString(@JsonProperty("cuisine") String cuisine) throws HttpMessageNotReadableException {
        return Arrays.stream(Cuisine.values())
                .filter(val -> val.getName().equalsIgnoreCase(cuisine) || val.toString().equalsIgnoreCase(cuisine))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}
