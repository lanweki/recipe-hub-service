package com.recipehub.lanweki.service;

import com.recipehub.lanweki.model.dto.RecipeDto;
import com.recipehub.lanweki.model.request.RecipeAddRequest;
import com.recipehub.lanweki.mapper.RecipeMapper;
import com.recipehub.lanweki.repository.RecipeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RecipeService {

    private final RecipeRepository repository;

    private final RecipeMapper mapper;

    private static final Logger logger = LogManager.getLogger(RecipeService.class);

    @Autowired
    public RecipeService(RecipeRepository repository, RecipeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public RecipeDto getById(String id) {
        var recipe = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Recipe does not exist with id=" + id));

        return mapper.entityToDto(recipe);
    }

    public List<RecipeDto> getAll() {
        var recipes = repository.findAll();

        return recipes.stream()
                .map(mapper::entityToDto)
                .toList();
    }

    public RecipeDto create(RecipeAddRequest recipe) {
        var entity = mapper.addRequestToEntity(recipe);
        var savedEntity = repository.save(entity);

        logger.info("The recipe was created, id={}", savedEntity.getId());
        return mapper.entityToDto(savedEntity);
    }
}
