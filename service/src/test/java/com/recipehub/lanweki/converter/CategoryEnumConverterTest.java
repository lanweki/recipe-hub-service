package com.recipehub.lanweki.converter;


import com.recipehub.lanweki.enums.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class CategoryEnumConverterTest {

    private CategoryEnumConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new CategoryEnumConverter();
    }

    @Test
    void testConvert_whenAllSuccessfullyConverted() {
        var values = List.of("breakfasts", "broths", "snacks", "beverages", "main_courses",
                "pasta_and_pizza", "risotto", "salads", "sauces_and_marinades", "soups", "sandwiches", "pastries_and_desserts");
        var enums = List.of(Category.BREAKFASTS, Category.BROTHS, Category.SNACKS, Category.BEVERAGES,
                Category.MAIN_COURSES, Category.PASTA_AND_PIZZA, Category.RISOTTO, Category.SALADS,
                Category.SAUCES_AND_MARINADES, Category.SOUPS, Category.SANDWICHES, Category.PASTRIES_AND_DESSERTS);

        for (int i = 0; i < values.size(); i++) {
            Assertions.assertEquals(enums.get(i), converter.convert(values.get(i)));
        }
    }
}
