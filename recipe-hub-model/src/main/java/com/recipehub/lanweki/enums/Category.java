package com.recipehub.lanweki.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.Arrays;

public enum Category {
    BREAKFASTS("breakfasts"),
    BROTHS("broths"),
    SNACKS("shacks"),
    BEVERAGES("beverages"),
    MAIN_COURSES("main courses"),
    PASTA_AND_PIZZA("pasta and pizza"),
    RISOTTO("risotto"),
    SALADS("salads"),
    SAUCES_AND_MARINADES("sauces and marinades"),
    SOUPS("soups"),
    SANDWICHES("sandwiches"),
    PASTRIES_AND_DESSERTS("pastries and desserts");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Category fromString(@JsonProperty("category") String category) throws HttpMessageNotReadableException {
        return Arrays.stream(Category.values())
                .filter(val -> val.getName().equalsIgnoreCase(category) || val.toString().equalsIgnoreCase(category))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }
}
