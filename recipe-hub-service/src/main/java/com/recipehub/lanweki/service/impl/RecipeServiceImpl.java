package com.recipehub.lanweki.service.impl;

import com.recipehub.lanweki.dto.RecipeDto;
import com.recipehub.lanweki.entity.Recipe;
import com.recipehub.lanweki.exception.BadRequestException;
import com.recipehub.lanweki.exception.NotFoundException;
import com.recipehub.lanweki.mapper.RecipeMapper;
import com.recipehub.lanweki.repository.RecipeRepository;
import com.recipehub.lanweki.repository.UserRepository;
import com.recipehub.lanweki.request.CommentAddRequest;
import com.recipehub.lanweki.request.RecipeAddRequest;
import com.recipehub.lanweki.service.RecipeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository repository;

    private final UserRepository userRepository;

    private final RecipeMapper mapper;

    private static final Logger logger = LogManager.getLogger(RecipeServiceImpl.class);

    public RecipeServiceImpl(RecipeRepository repository, UserRepository userRepository, RecipeMapper mapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public RecipeDto getById(Integer id) {
        var recipe = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        return mapper.entityToDto(recipe);
    }

    @Override
    public List<RecipeDto> getAll(Integer page, Integer size) {
        var recipes = repository.findAll(PageRequest.of(page, size));
        return getRecipeDtos(recipes);
    }

    @Override
    @Transactional
    public void create(RecipeAddRequest recipeAddRequest) {
        var userId = recipeAddRequest.getUserId();
        userRepository.findById(userId)
                .orElseThrow(() -> new BadRequestException("User with such id does not exist, id=" + userId));

        var recipe = mapper.addRequestToEntity(recipeAddRequest);
        var savedRecipe = repository.save(recipe);

        var dto = mapper.entityToDto(savedRecipe);
        logger.info("The new recipe was created. {}", dto);
    }

    @Override
    public List<RecipeDto> searchByName(String name, Integer page, Integer size) {
        var recipes = repository.findAllByName(name, PageRequest.of(page, size));
        return getRecipeDtos(recipes);
    }

    @Override
    public List<RecipeDto> searchByCuisine(String cuisine, Integer page, Integer size) {
        var recipes = repository.findAllByCuisine(cuisine, PageRequest.of(page, size));
        return getRecipeDtos(recipes);
    }

    @Override
    public List<RecipeDto> searchByCategory(String category, Integer page, Integer size) {
        var recipes = repository.findAllByCategory(category, PageRequest.of(page, size));
        return getRecipeDtos(recipes);
    }

    @Override
    public List<RecipeDto> searchCategoryAndCuisine(String category, String cuisine, Integer page, Integer size) {
        var recipes = repository.findAllByCategoryAndCuisine(category, cuisine, PageRequest.of(page, size));
        return getRecipeDtos(recipes);
    }

    @Override
    public List<RecipeDto> searchByPattern(String pattern, Integer page, Integer size) {
        var recipes = repository.findAllByPattern(pattern, PageRequest.of(page, size));
        return getRecipeDtos(recipes);
    }

    @Transactional
    @Override
    public void addComment(CommentAddRequest request) {
        //checks
        repository.addCommentToRecipe(
                Integer.parseInt(request.getUserId()),
                Integer.parseInt(request.getRecipeId()),
                request.getComment());
    }

    private List<RecipeDto> getRecipeDtos(Page<Recipe> recipes) {
        return recipes.stream()
                .map(mapper::entityToDto)
                .toList();
    }

}
