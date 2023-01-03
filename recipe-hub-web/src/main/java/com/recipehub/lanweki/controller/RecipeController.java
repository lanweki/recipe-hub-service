package com.recipehub.lanweki.controller;

import com.recipehub.lanweki.dto.RecipeDto;
import com.recipehub.lanweki.enums.Category;
import com.recipehub.lanweki.enums.Cuisine;
import com.recipehub.lanweki.request.CommentAddRequest;
import com.recipehub.lanweki.request.RecipeAddRequest;
import com.recipehub.lanweki.response.RecipeHubResponse;
import com.recipehub.lanweki.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getById(@PathVariable Integer id) {
        var recipe = service.getById(id);

        return ResponseEntity.ok(recipe);
    }

    @GetMapping
    public ResponseEntity<List<RecipeDto>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "5") Integer size) {
        var recipe = service.getAll(page, size);

        return ResponseEntity.ok(recipe);
    }

    @GetMapping(params = "pattern")
    public ResponseEntity<List<RecipeDto>> search(@RequestParam String pattern,
                                                  @RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "5") Integer size) {
        var recipes = service.searchByPattern(pattern, page, size);

        return ResponseEntity.ok(recipes);
    }

    @GetMapping(params = "category")
    public ResponseEntity<List<RecipeDto>> search(@RequestParam Category category,
                                                  @RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "5") Integer size) {
        var recipe = service.searchByCategory(category.getName(), page, size);

        return ResponseEntity.ok(recipe);
    }

    @GetMapping(params = "cuisine")
    public ResponseEntity<List<RecipeDto>> search(@RequestParam Cuisine cuisine,
                                                  @RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "5") Integer size) {
        var recipe = service.searchByCuisine(cuisine.getName(), page, size);

        return ResponseEntity.ok(recipe);
    }

    @GetMapping(params = {"cuisine", "category"})
    public ResponseEntity<List<RecipeDto>> search(@RequestParam Cuisine cuisine,
                                                  @RequestParam Category category,
                                                  @RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "5") Integer size) {
        var recipe = service.searchCategoryAndCuisine(category.getName(), cuisine.getName(), page, size);

        return ResponseEntity.ok(recipe);
    }

    @PostMapping
    public ResponseEntity<RecipeHubResponse> create(@Valid @RequestBody RecipeAddRequest recipeAddRequest) {
        service.create(recipeAddRequest);

        var response = new RecipeHubResponse("New recipe was successfully added.", Collections.emptyMap());
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/comments")
    public ResponseEntity<RecipeHubResponse> addCommentToRecipe(@RequestBody CommentAddRequest request) {
        service.addComment(request);

        var response = new RecipeHubResponse("New comment was successfully added.", Collections.emptyMap());
        return ResponseEntity.status(201).body(response);
    }
}
