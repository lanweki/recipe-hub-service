package com.recipehub.lanweki.repository;

import com.recipehub.lanweki.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    Page<Recipe> findAll(Pageable pageable);

    @Query("SELECT r FROM Recipe r WHERE lower(r.name) like '%lower(:name)%'")
    Page<Recipe> findAllByName(String name, Pageable pageable);

    Page<Recipe> findAllByCategory(String category, Pageable pageable);

    Page<Recipe> findAllByCuisine(String cuisine, Pageable pageable);

    Page<Recipe> findAllByCategoryAndCuisine(String category, String cuisine, Pageable pageable);

    @Query("SELECT r FROM Recipe r WHERE lower(r.name) like lower(concat('%', :pattern,'%')) " +
            "OR lower(r.category) like lower(concat('%', :pattern,'%')) " +
            "OR lower(r.cuisine) like lower(concat('%', :pattern,'%'))")
    Page<Recipe> findAllByPattern(@Param("pattern") String pattern, Pageable pageable);

    @Modifying
    @Query(value = "INSERT INTO comment (user_id, comment_text, recipe_id) VALUES (:userId, :comment, :recipeId)", nativeQuery = true)
    void addCommentToRecipe(Integer userId, Integer recipeId, String comment);
}
