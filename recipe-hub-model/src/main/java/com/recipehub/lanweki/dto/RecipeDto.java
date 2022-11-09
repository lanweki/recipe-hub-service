package com.recipehub.lanweki.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RecipeDto(
        int id,
        String name,
        String category,
        String cuisine,
        String ingredients,
        String description,
        String instruction,
        LocalDateTime createdDate,
        UserDto createdBy,
        String pictureUrl,
        List<CommentDto> comments) {
}
