package com.recipehub.lanweki.service.impl;

import com.recipehub.lanweki.dto.RecipeDto;
import com.recipehub.lanweki.entity.User;
import com.recipehub.lanweki.exception.BadRequestException;
import com.recipehub.lanweki.exception.NotFoundException;
import com.recipehub.lanweki.mapper.RecipeMapper;
import com.recipehub.lanweki.mapper.UserMapper;
import com.recipehub.lanweki.repository.UserRepository;
import com.recipehub.lanweki.request.LoginRequest;
import com.recipehub.lanweki.request.RecipeBookAddRequest;
import com.recipehub.lanweki.request.RegisterRequest;
import com.recipehub.lanweki.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserMapper userMapper;

    private final RecipeMapper recipeMapper;

    public UserServiceImpl(UserRepository repository, UserMapper userMapper, RecipeMapper recipeMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
        this.recipeMapper = recipeMapper;
    }

    @Transactional
    @Override
    public void register(RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();

        Optional<User> existedUser = repository.getUserByUsername(username);

        if (existedUser.isPresent()) {
            throw new BadRequestException("User with such username already exists, username=" + username);
        }

        User user = userMapper.registerRequestToEntity(registerRequest);

        repository.save(user);
    }

    @Override
    public int login(LoginRequest loginRequest) {
        var user = repository.getByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword())
                .orElseThrow(() -> new NotFoundException("Credentials are not valid or user is not exist"));
        return user.getId();
    }

    @Transactional
    @Override
    public void addRecipeToRecipeBook(RecipeBookAddRequest request) {
        repository.addRecipeToRecipeBook(request.getRecipeId(), request.getUserId());
    }

    @Override
    public List<RecipeDto> getRecipeBookByUserId(int userId) {
        User user = repository.getUserById(userId).orElseThrow(() -> new NotFoundException("Credentials are not valid or user is not exist"));

        var recipeBook = user.getRecipeBook();

        return recipeBook.stream()
                .map(recipeMapper::entityToDto)
                .toList();
    }
}
