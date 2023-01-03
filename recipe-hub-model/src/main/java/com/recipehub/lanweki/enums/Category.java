package com.recipehub.lanweki.enums;

import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.Arrays;

public enum Category {
    BREAKFASTS("breakfasts"),
    BROTHS("broths"),
    SNACKS("snacks"),
    BEVERAGES("beverages"),
    MAIN_COURSES("main_courses"),
    PASTA_AND_PIZZA("pasta_and_pizza"),
    RISOTTO("risotto"),
    SALADS("salads"),
    SAUCES_AND_MARINADES("sauces_and_marinades"),
    SOUPS("soups"),
    SANDWICHES("sandwiches"),
    PASTRIES_AND_DESSERTS("pastries_and_desserts");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Category fromString(String value) throws HttpMessageNotReadableException {
        return Arrays.stream(Category.values())
                .filter(val -> val.getName().equalsIgnoreCase(value) || val.toString().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}
