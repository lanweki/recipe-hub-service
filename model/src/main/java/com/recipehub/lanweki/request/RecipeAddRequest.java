package com.recipehub.lanweki.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RecipeAddRequest {

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String category;

    @NotBlank
    @NotNull
    private String cuisine;

    @NotBlank
    @NotNull
    private String ingredients;

    @NotBlank
    @NotNull
    private String description;

    @NotBlank
    @NotNull
    private String instruction;

    @NotNull
    private Integer userId;

    @Nullable
    private String pictureUrl;
}
