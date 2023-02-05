package com.recipehub.lanweki.service;

import com.recipehub.lanweki.dto.RecipeDto;
import com.recipehub.lanweki.entity.Recipe;
import com.recipehub.lanweki.entity.User;
import com.recipehub.lanweki.exception.BadRequestException;
import com.recipehub.lanweki.exception.NotFoundException;
import com.recipehub.lanweki.mapper.RecipeMapper;
import com.recipehub.lanweki.mapper.UserMapper;
import com.recipehub.lanweki.repository.UserRepository;
import com.recipehub.lanweki.request.LoginRequest;
import com.recipehub.lanweki.request.RegisterRequest;
import com.recipehub.lanweki.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private RecipeMapper recipeMapper;

    @Test
    void testRegister_whenSuccessfullyRegistered() {
        var username = "username";
        var user = new User();
        user.setUsername(username);
        var request = new RegisterRequest("username", null, null, null);

        when(repository.getUserByUsername(username)).thenReturn(Optional.empty());
        when(userMapper.registerRequestToEntity(request)).thenReturn(user);

        userService.register(request);

        verify(repository).save(user);
    }

    @Test
    void testRegister_whenExceptionIsThrown() {
        var username = "username";
        var user = new User();
        user.setUsername(username);
        var request = new RegisterRequest("username", null, null, null);

        when(repository.getUserByUsername(username)).thenReturn(Optional.of(user));

        Assertions.assertThrows(BadRequestException.class, () -> userService.register(request));
    }

    @Test
    void testLogin_whenSuccess() {
        var request = new LoginRequest("username", "password");
        var user = new User();
        user.setId(1);

        when(repository.getByUsernameAndPassword("username", "password")).thenReturn(Optional.of(user));

        int actualResult = userService.login(request);

        Assertions.assertEquals(1, actualResult);
    }

    @Test
    void testLogin_whenExceptionIsThrown() {
        var request = new LoginRequest("username", "password");

        when(repository.getByUsernameAndPassword("username", "password")).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> userService.login(request));
    }

//    @Test
//    void testAddRecipeToRecipeBook_whenSuccess() {
//        userService.addRecipeToRecipeBook(1, 1);
//
//        verify(repository).addRecipeToRecipeBook(1, 1);
//    }

    @Test
    void testGetRecipeByUserId_whenSuccess() {
        int userId = 1;
        var user = new User();
        user.setId(userId);
        var recipe = new Recipe();
        var recipeDto = new RecipeDto();
        recipe.setId(1);
        recipeDto.setId(1);

        user.setRecipeBook(List.of(recipe));

        when(repository.getUserById(userId)).thenReturn(Optional.of(user));
        when(recipeMapper.entityToDto(recipe)).thenReturn(recipeDto);

        var actualRecipeBook = userService.getRecipeBookByUserId(userId);

        Assertions.assertEquals(List.of(recipeDto), actualRecipeBook);
    }

    @Test
    void testGetRecipeByUserId_whenExceptionIsThrown() {
        int userId = 111;
        when(repository.getUserById(userId)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> userService.getRecipeBookByUserId(userId));
    }
}
