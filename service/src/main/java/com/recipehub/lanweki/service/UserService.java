package com.recipehub.lanweki.service;

import com.recipehub.lanweki.dto.RecipeDto;
import com.recipehub.lanweki.request.LoginRequest;
import com.recipehub.lanweki.request.RecipeBookAddRequest;
import com.recipehub.lanweki.request.RegisterRequest;

import java.util.List;

public interface UserService {

    void register(RegisterRequest registerRequest);

    int login(LoginRequest loginRequest);

    void addRecipeToRecipeBook(RecipeBookAddRequest request);

    List<RecipeDto> getRecipeBookByUserId(int userId);
}
