package com.recipehub.lanweki.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record UserDto(
        int id,
        String username,
        String firstName,
        String lastName) {
}
