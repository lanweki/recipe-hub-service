package com.recipehub.lanweki.repository;

import com.recipehub.lanweki.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getByUsernameAndPassword(String username, String password);

    @Modifying
    @Query(value = "INSERT INTO user_recipe_book (user_id, recipe_id) VALUES (:userId, :recipeId)", nativeQuery = true)
    void addRecipeToRecipeBook(int recipeId, int userId);

    Optional<User> getUserById(int id);

    Optional<User> getUserByUsername(String username);
}
