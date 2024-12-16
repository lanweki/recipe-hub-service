package com.recipehub.lanweki.converter;

import com.recipehub.lanweki.enums.Cuisine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class CuisineEnumConverterTest {

    private CuisineEnumConverter converter;

    @BeforeEach
    public void setUp() {
        converter = new CuisineEnumConverter();
    }

    @Test
    void testConvert_whenAllSuccessfullyConverted() {
        var values = List.of("italian", "georgian", "chinese", "french", "japanese", "indian", "american",
                "spanish", "greek", "other");
        var enums = List.of(Cuisine.ITALIAN, Cuisine.GEORGIAN, Cuisine.CHINESE, Cuisine.FRENCH, Cuisine.JAPANESE,
                Cuisine.INDIAN, Cuisine.AMERICAN, Cuisine.SPANISH, Cuisine.GREEK, Cuisine.OTHER);

        for (int i = 0; i < values.size(); i++) {
            Assertions.assertEquals(enums.get(i), converter.convert(values.get(i)));
        }
    }
}
