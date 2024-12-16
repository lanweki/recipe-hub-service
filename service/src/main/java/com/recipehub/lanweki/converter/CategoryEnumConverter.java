package com.recipehub.lanweki.converter;

import com.recipehub.lanweki.enums.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryEnumConverter implements Converter<String, Category> {

    @Override
    public Category convert(String value) {
        return Category.fromString(value);
    }
}
