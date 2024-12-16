package com.recipehub.lanweki.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CommentAddRequest {
    @NotBlank
    @NotNull
    private String userId;

    @NotBlank
    @NotNull
    private String recipeId;

    @NotBlank
    @NotNull
    private String comment;
}
