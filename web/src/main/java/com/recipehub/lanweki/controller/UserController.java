package com.recipehub.lanweki.controller;

import com.recipehub.lanweki.dto.RecipeDto;
import com.recipehub.lanweki.request.LoginRequest;
import com.recipehub.lanweki.request.RecipeBookAddRequest;
import com.recipehub.lanweki.request.RegisterRequest;
import com.recipehub.lanweki.response.RecipeHubResponse;
import com.recipehub.lanweki.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/verify")
    public ResponseEntity<RecipeHubResponse> login(@RequestBody LoginRequest loginRequest) {
        var userId = service.login(loginRequest);
        var response = new RecipeHubResponse("Successfully logged in.", Map.of("userId", String.valueOf(userId)));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<RecipeHubResponse> register(@RequestBody RegisterRequest registerRequest) {
        service.register(registerRequest);
        var response = new RecipeHubResponse("Successfully registered.", Collections.emptyMap());

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/users/{id}/recipebook")
    public ResponseEntity<List<RecipeDto>> getRecipeBook(@PathVariable String id) {
        var recipeBook = service.getRecipeBookByUserId(Integer.parseInt(id));

        return ResponseEntity.ok(recipeBook);
    }

    @PostMapping("/users/{id}/recipebook")
    public ResponseEntity<RecipeHubResponse> addRecipeToRecipeBook(@RequestBody RecipeBookAddRequest recipeBookAddRequest) {
        service.addRecipeToRecipeBook(recipeBookAddRequest);

        var response = new RecipeHubResponse("Successfully added.", Collections.emptyMap());

        return ResponseEntity.status(201).body(response);
    }
}
