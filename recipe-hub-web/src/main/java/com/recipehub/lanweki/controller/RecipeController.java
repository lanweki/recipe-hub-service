package com.recipehub.lanweki.controller;

import com.recipehub.lanweki.dto.RecipeDto;
import com.recipehub.lanweki.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService service;

    @Autowired
    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getById(@PathVariable Integer id) {
        var recipe = service.getById(id);

        return ResponseEntity.ok(recipe);
    }

}
