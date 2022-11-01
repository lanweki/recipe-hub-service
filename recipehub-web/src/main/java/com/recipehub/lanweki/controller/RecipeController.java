package com.recipehub.lanweki.controller;

import com.recipehub.lanweki.model.dto.RecipeDto;
import com.recipehub.lanweki.model.request.RecipeAddRequest;
import com.recipehub.lanweki.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService service;

    @Autowired
    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getById(@PathVariable String id) {
        var recipe = service.getById(id);

        return ResponseEntity.ok(recipe);
    }

    @GetMapping
    public ResponseEntity<List<RecipeDto>> getAll() {
        var recipes = service.getAll();

        return ResponseEntity.ok(recipes);
    }

    @PostMapping
    public ResponseEntity<RecipeDto> create(@Valid @RequestBody RecipeAddRequest recipeAddRequest) {
        var savedRecipe = service.create(recipeAddRequest);

        return ResponseEntity.ok(savedRecipe);
    }
}
