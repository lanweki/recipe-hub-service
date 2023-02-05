package com.recipehub.lanweki.entity;

import com.recipehub.lanweki.enums.UserRole;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipe_hub_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "user_password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_role")
    private String userRole;

    @ManyToMany
    @JoinTable(
            name = "user_recipe_book",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "recipe_id")}
    )
    private List<Recipe> recipeBook;

    @OneToMany(mappedBy = "createdBy")
    private List<Recipe> createdRecipes;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @PrePersist
    public void onPrePersist() {
        if (userRole == null) userRole = UserRole.USER.name();
    }
}
