package com.recipehub.lanweki.mapper;

import com.recipehub.lanweki.dto.CommentDto;
import com.recipehub.lanweki.dto.RecipeDto;
import com.recipehub.lanweki.dto.UserDto;
import com.recipehub.lanweki.entity.Recipe;
import com.recipehub.lanweki.entity.User;
import com.recipehub.lanweki.request.RecipeAddRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class RecipeMapper {
    public RecipeDto entityToDto(Recipe entity) {
        return new RecipeDto(entity.getId(),
                entity.getName(),
                getStringFromEnum(entity.getCategory()),
                getStringFromEnum(entity.getCuisine()),
                entity.getIngredientsDescription(),
                entity.getDescription(),
                entity.getInstruction(),
                entity.getCreateDate(),
                getUserDto(entity),
                entity.getPictureUrl(),
                getComments(entity)
        );
    }

    public Recipe addRequestToEntity(RecipeAddRequest recipeAddRequest) {
        Recipe recipe = new Recipe();

        recipe.setName(recipeAddRequest.getName());
        recipe.setCategory(recipeAddRequest.getCategory());
        recipe.setCuisine(recipeAddRequest.getCuisine());
        recipe.setIngredientsDescription(recipeAddRequest.getIngredients());
        recipe.setDescription(recipeAddRequest.getIngredients());
        recipe.setInstruction(recipeAddRequest.getInstruction());

        User user = new User();
        user.setId(recipeAddRequest.getUserId());

        recipe.setCreatedBy(user);
        recipe.setPictureUrl(recipe.getPictureUrl());

        return recipe;
    }

    private List<CommentDto> getComments(Recipe entity) {
        if (entity.getComments() == null) return Collections.emptyList();
        return entity.getComments().stream()
                .map(comment -> new CommentDto(comment.getText(), comment.getUser().getUsername()))
                .toList();
    }

    private UserDto getUserDto(Recipe entity) {
        return new UserDto(
                entity.getCreatedBy().getId(),
                entity.getCreatedBy().getUsername(),
                entity.getCreatedBy().getFirstName(),
                entity.getCreatedBy().getLastName());
    }

    private String getStringFromEnum(String enumString) {
        return enumString.replace("_", " ");
    }
}
