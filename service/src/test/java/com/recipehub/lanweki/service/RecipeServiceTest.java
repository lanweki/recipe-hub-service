package com.recipehub.lanweki.service;

import com.recipehub.lanweki.dto.RecipeDto;
import com.recipehub.lanweki.entity.Recipe;
import com.recipehub.lanweki.entity.User;
import com.recipehub.lanweki.enums.Category;
import com.recipehub.lanweki.enums.Cuisine;
import com.recipehub.lanweki.exception.BadRequestException;
import com.recipehub.lanweki.exception.NotFoundException;
import com.recipehub.lanweki.mapper.RecipeMapper;
import com.recipehub.lanweki.repository.RecipeRepository;
import com.recipehub.lanweki.repository.UserRepository;
import com.recipehub.lanweki.request.RecipeAddRequest;
import com.recipehub.lanweki.service.impl.RecipeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Mock
    private RecipeRepository repository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RecipeMapper mapper;


    @Test
    void testGetById_whenSuccess() {
        int id = 1;
        var recipe = new Recipe();
        recipe.setId(id);
        var expectedRecipeDto = new RecipeDto();
        expectedRecipeDto.setId(1);

        when(repository.findById(id)).thenReturn(Optional.of(recipe));
        when(mapper.entityToDto(recipe)).thenReturn(expectedRecipeDto);

        var actualRecipeDto = recipeService.getById(id);

        Assertions.assertEquals(expectedRecipeDto, actualRecipeDto);
    }

    @Test
    void testGetById_whenExceptionIdThrown() {
        int id = 1;
        var recipe = new Recipe();
        recipe.setId(id);

        when(repository.findById(id)).thenThrow(NotFoundException.class);

        Assertions.assertThrows(NotFoundException.class, () -> recipeService.getById(id));
        verify(mapper, never()).entityToDto(recipe);
    }

    @Test
    void testGetAll_whenSuccess() {
        int id = 1;
        var recipe = new Recipe();
        recipe.setId(id);
        var recipes = List.of(recipe);
        var expectedRecipeDto = new RecipeDto();
        expectedRecipeDto.setId(id);

        when(repository.findAll(PageRequest.of(0, 1))).thenReturn(new PageImpl<>(recipes));
        when(mapper.entityToDto(recipe)).thenReturn(expectedRecipeDto);

        var actualRecipes = recipeService.getAll(0, 1);

        Assertions.assertEquals(List.of(expectedRecipeDto), actualRecipes);
    }

    @Test
    void testCreate_whenSuccessfullyCreated() {
        int userId = 1;
        var request = new RecipeAddRequest();
        request.setName("Test");
        request.setUserId(userId);
        var recipe = new Recipe();
        recipe.setName("Test");
        var recipeDto = new RecipeDto();
        recipe.setName("Test");


        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        when(mapper.addRequestToEntity(request)).thenReturn(recipe);
        when(repository.save(recipe)).thenReturn(recipe);
        when(mapper.entityToDto(recipe)).thenReturn(recipeDto);

        recipeService.create(request);

        verify(repository).save(recipe);
    }

    @Test
    void testCreate_whenUserIsAlreadyExist() {
        int userId = 111;
        var request = new RecipeAddRequest();
        request.setUserId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Assertions.assertThrows(BadRequestException.class, () -> recipeService.create(request));

        verifyNoInteractions(mapper);
        verifyNoInteractions(repository);
    }

    @Test
    void testSearchByName_whenSuccess() {
        var recipe = new Recipe();
        var recipes = List.of(recipe);
        var name = "test";
        var expectedRecipeDto = new RecipeDto();
        expectedRecipeDto.setName(name);
        recipe.setName(name);

        when(repository.findAllByName(name, PageRequest.of(0, 1))).thenReturn(new PageImpl<>(recipes));
        when(mapper.entityToDto(recipe)).thenReturn(expectedRecipeDto);

        var actualRecipes = recipeService.searchByName(name, 0, 1);

        Assertions.assertEquals(List.of(expectedRecipeDto), actualRecipes);
    }

    @Test
    void testSearchByCuisine_whenSuccess() {
        var recipe = new Recipe();
        var recipes = List.of(recipe);
        var cuisine = Cuisine.FRENCH.getName();
        var expectedRecipeDto = new RecipeDto();
        expectedRecipeDto.setCuisine(cuisine);
        recipe.setCuisine(cuisine);

        when(repository.findAllByCuisine(cuisine, PageRequest.of(0, 1))).thenReturn(new PageImpl<>(recipes));
        when(mapper.entityToDto(recipe)).thenReturn(expectedRecipeDto);

        var actualRecipes = recipeService.searchByCuisine(cuisine, 0, 1);

        Assertions.assertEquals(List.of(expectedRecipeDto), actualRecipes);
    }

    @Test
    void testSearchByCategory_whenSuccess() {
        var recipe = new Recipe();
        var recipes = List.of(recipe);
        var category = Category.SALADS.getName();
        var expectedRecipeDto = new RecipeDto();
        expectedRecipeDto.setCategory(category);
        recipe.setCategory(category);
        when(repository.findAllByCategory(category, PageRequest.of(0, 1))).thenReturn(new PageImpl<>(recipes));
        when(mapper.entityToDto(recipe)).thenReturn(expectedRecipeDto);

        var actualRecipes = recipeService.searchByCategory(category, 0, 1);

        Assertions.assertEquals(List.of(expectedRecipeDto), actualRecipes);
    }

    @Test
    void testSearchCategoryAndCuisine_whenSuccess() {
        var recipe = new Recipe();
        var recipes = List.of(recipe);
        var category = Category.SALADS.getName();
        var cuisine = Cuisine.FRENCH.getName();
        var expectedRecipeDto = new RecipeDto();
        expectedRecipeDto.setCategory(category);
        recipe.setCategory(category);

        expectedRecipeDto.setCuisine(cuisine);
        recipe.setCuisine(cuisine);

        when(repository.findAllByCategoryAndCuisine(category, cuisine, PageRequest.of(0, 1))).thenReturn(new PageImpl<>(recipes));
        when(mapper.entityToDto(recipe)).thenReturn(expectedRecipeDto);

        var actualRecipes = recipeService.searchCategoryAndCuisine(category, cuisine, 0, 1);

        Assertions.assertEquals(List.of(expectedRecipeDto), actualRecipes);
    }

    @Test
    void testSearchByPattern_whenSuccess() {
        var pattern = "TEST";
        var recipe = new Recipe();
        var recipes = List.of(recipe);
        var expectedRecipeDto = new RecipeDto();

        recipe.setName("test");
        expectedRecipeDto.setName("test");

        when(repository.findAllByPattern(pattern, PageRequest.of(0, 1))).thenReturn(new PageImpl<>(recipes));
        when(mapper.entityToDto(recipe)).thenReturn(expectedRecipeDto);

        var actualRecipes = recipeService.searchByPattern(pattern, 0, 1);

        Assertions.assertEquals(List.of(expectedRecipeDto), actualRecipes);
    }
}
