package com.blogApp.blogApp.service;

import com.blogApp.blogApp.controller.Response;
import com.blogApp.blogApp.dto.AllCategory;
import com.blogApp.blogApp.dto.CategoryDto;

public interface CategoryService {

    public Response createCat(CategoryDto categoryDto);

    public Response updateCat(CategoryDto categoryDto,Long catId);

    public Response deleteCat(Long catId);

    public CategoryDto getCatById(Long id);
    public AllCategory getAllCat();

}
