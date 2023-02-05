package com.recipehub.lanweki.service;

import com.recipehub.lanweki.dto.RecipeDto;
import com.recipehub.lanweki.request.CommentAddRequest;
import com.recipehub.lanweki.request.RecipeAddRequest;

import java.util.List;;

public interface RecipeService {

    RecipeDto getById(Integer id);

    List<RecipeDto> getAll(Integer page, Integer size);

    void create(RecipeAddRequest recipeAddRequest);

    List<RecipeDto> searchByName(String name, Integer page, Integer size);

    List<RecipeDto> searchByCuisine(String cuisine, Integer page, Integer size);

    List<RecipeDto> searchByCategory(String category, Integer page, Integer size);

    List<RecipeDto> searchCategoryAndCuisine(String category, String cuisine, Integer page, Integer size);

    List<RecipeDto> searchByPattern(String pattern, Integer page, Integer size);

    void addComment(CommentAddRequest request);
}
