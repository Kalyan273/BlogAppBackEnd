package com.blogApp.blogApp.controller;

import com.blogApp.blogApp.dto.AllCategory;
import com.blogApp.blogApp.dto.CategoryDto;
import com.blogApp.blogApp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCat")
    public ResponseEntity<Response> createPost(@Valid @RequestBody CategoryDto categoryDto) throws Exception {

        if(null!=categoryDto){
            Response response = categoryService.createCat(categoryDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else throw new Exception("invalid data");

    }
    @PostMapping("/updateCat/{id}")
    public ResponseEntity<Response> updateUser(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Long id) throws Exception {

        if(null!=categoryDto || null!=id){
            Response response = categoryService.updateCat(categoryDto,id);
            return ResponseEntity.ok(response);
        }else throw new Exception("invalid data");

    }
    @PostMapping("/deleteCat/{id}")
    public ResponseEntity<Response> deleteUser(Long id) throws Exception {

        if(null!=id){
            Response response = categoryService.deleteCat(id);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        }else throw new Exception("not found");

    }
    @PostMapping("/allCat")
    public ResponseEntity<AllCategory> allCat() throws Exception {


        AllCategory allCategory = categoryService.getAllCat();
        return new ResponseEntity<>(allCategory,HttpStatus.ACCEPTED);

    }
    @PostMapping("/byCatId/{id}")
    public ResponseEntity<CategoryDto> catById(Long id) throws Exception {

        if(null!=id){
            CategoryDto categoryDto = categoryService.getCatById(id);
            return new ResponseEntity<>(categoryDto,HttpStatus.ACCEPTED);
        }else throw new Exception("invalid data");

    }
}
