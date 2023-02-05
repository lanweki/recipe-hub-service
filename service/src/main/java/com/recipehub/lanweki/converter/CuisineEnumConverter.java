package com.recipehub.lanweki.converter;

import com.recipehub.lanweki.enums.Cuisine;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CuisineEnumConverter implements Converter<String, Cuisine> {
    @Override
    public Cuisine convert(String value) {
        return Cuisine.fromString(value);
    }
}
