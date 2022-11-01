package com.recipehub.lanweki.mapper;

import com.recipehub.lanweki.model.dto.RecipeDto;
import com.recipehub.lanweki.model.entity.Recipe;
import com.recipehub.lanweki.model.request.RecipeAddRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    RecipeDto entityToDto(Recipe entity);

    Recipe dtoToEntity(RecipeDto dto);

    Recipe addRequestToEntity(RecipeAddRequest addRequest);
}
