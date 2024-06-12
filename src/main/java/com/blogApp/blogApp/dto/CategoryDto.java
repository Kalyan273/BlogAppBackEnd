package com.blogApp.blogApp.dto;


import com.blogApp.blogApp.controller.Response;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto extends Response {

    private Long catId;
    @Size( max = 30, message = "Title must be with in 30 characters")
    private String categoryTitle;
    private String categoryDesc;
}
