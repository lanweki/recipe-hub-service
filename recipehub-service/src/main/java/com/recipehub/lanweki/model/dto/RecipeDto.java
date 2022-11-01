package com.recipehub.lanweki.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RecipeDto(
        String id,
        String name,
        String category,
        String cuisine,
        List<String> ingredients,
        List<String> instruction,
        LocalDateTime createdDate) {
}
