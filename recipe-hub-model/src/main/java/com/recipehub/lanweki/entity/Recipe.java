package com.recipehub.lanweki.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "recipe_name")
    private String name;

    @Column(name = "recipe_category")
    private String category;

    @Column(name = "cuisine")
    private String cuisine;

    @Column(name = "ingredients_description")
    private String ingredientsDescription;

    @Column(name = "description")
    private String description;

    @Column(name = "instruction_description")
    private String instruction;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "picture_url")
    private String pictureUrl;

    @ManyToMany(mappedBy = "recipeBook")
    private List<User> users;

    @OneToMany(mappedBy = "recipe")
    private List<Comment> comments;

    @PrePersist
    public void onPrePersist() {
        createDate = LocalDateTime.now();
    }
}
