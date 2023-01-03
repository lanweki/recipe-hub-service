package com.recipehub.lanweki.controller;

import com.recipehub.lanweki.dto.RecipeDto;
import com.recipehub.lanweki.request.LoginRequest;
import com.recipehub.lanweki.request.RegisterRequest;
import com.recipehub.lanweki.response.RecipeHubResponse;
import com.recipehub.lanweki.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<RecipeHubResponse> login(@RequestBody LoginRequest loginRequest) {
        var userId = service.login(loginRequest);
        var response = new RecipeHubResponse("Successfully logged in.", Map.of("userId", String.valueOf(userId)));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RecipeHubResponse> register(@RequestBody RegisterRequest registerRequest) {
        service.register(registerRequest);
        var response = new RecipeHubResponse("Successfully registered.", Collections.emptyMap());

        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/users/{id}/recipebookadd/{recipeId}")
    public ResponseEntity<RecipeHubResponse> addRecipeToRecipeBook(@PathVariable String id, @PathVariable String recipeId) {
        service.addRecipeToRecipeBook(Integer.parseInt(id), Integer.parseInt(recipeId));

        var response = new RecipeHubResponse("Successfully added.", Collections.emptyMap());

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/user/{id}/recipebook")
    public ResponseEntity<List<RecipeDto>> getRecipeBook(@PathVariable String id) {
        var recipeBook = service.getRecipeBookByUserId(Integer.parseInt(id));

        return ResponseEntity.ok(recipeBook);
    }
}
