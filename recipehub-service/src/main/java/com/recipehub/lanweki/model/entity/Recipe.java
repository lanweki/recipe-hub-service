package com.recipehub.lanweki.model.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "recipes")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Recipe {

    @MongoId
    private String id;

    private String name;

    private String category;

    private String cuisine;

    private List<String> ingredients;

    private List<String> instruction;

    @CreatedDate
    private LocalDateTime createdDate;
}
