package com.recipehub.lanweki.model.request;

import com.recipehub.lanweki.model.enums.Category;
import com.recipehub.lanweki.model.enums.Cuisine;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public record RecipeAddRequest(

        @NotNull
        @NotBlank
        String name,

        @NotNull
        Category category,

        @NotNull
        Cuisine cuisine,

        @NotNull
        @NotEmpty
        List<String> ingredients,

        @NotNull
        List<String> instruction) {
}
