package com.recipehub.lanweki.mapper;

import com.recipehub.lanweki.dto.CommentDto;
import com.recipehub.lanweki.dto.RecipeDto;
import com.recipehub.lanweki.dto.UserDto;
import com.recipehub.lanweki.entity.Recipe;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecipeMapper {
    public RecipeDto entityToDto(Recipe entity) {
        return new RecipeDto(entity.getId(),
                entity.getName(),
                entity.getCategory(),
                entity.getCuisine(),
                entity.getIngredientsDescription(),
                entity.getDescription(),
                entity.getInstruction(),
                entity.getCreateDate(),
                getUserDto(entity),
                entity.getPictureUrl(),
                getComments(entity)
                );
    }

    private List<CommentDto> getComments(Recipe entity) {
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

//    Recipe dtoToEntity(RecipeDto dto) {
//
//    }
}
