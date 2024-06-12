package com.blogApp.blogApp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CATEGORIES")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catId;
    private String categoryTitle;
    private String categoryDesc;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Post> post;


}
