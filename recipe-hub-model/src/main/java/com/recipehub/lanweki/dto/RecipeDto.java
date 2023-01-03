package com.recipehub.lanweki.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RecipeDto {
    private int id;
    private String name;
    private String category;
    private String cuisine;
    private String ingredients;
    private String description;
    private String instruction;
    private LocalDateTime createdDate;
    private UserDto createdBy;
    private String pictureUrl;
    private List<CommentDto> comments;
}
