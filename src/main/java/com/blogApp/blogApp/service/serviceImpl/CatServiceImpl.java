package com.blogApp.blogApp.service.serviceImpl;


import com.blogApp.blogApp.controller.Response;
import com.blogApp.blogApp.dto.AllCategory;
import com.blogApp.blogApp.dto.CategoryDto;
import com.blogApp.blogApp.entity.Category;
import com.blogApp.blogApp.exception.ResourceNotFound;
import com.blogApp.blogApp.mapper.UserMapper;
import com.blogApp.blogApp.repo.CategoryRepo;
import com.blogApp.blogApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Response createCat(CategoryDto categoryDto) {
        Category save =null;
        if(null!= categoryDto){
            Category category = userMapper.categoryDtoToCategory(categoryDto);
            save = categoryRepo.save(category);
        }
        Response response= new Response();
        if(null!=save ){
            response.setMessage("category saved ");
            response.setCode(HttpStatus.OK.value());
            response.setStatus(true);
        }
        return response;
    }

    @Override
    public Response updateCat(CategoryDto categoryDto, Long catId) {
        Category save =null;
        if(null!=categoryDto && null!=catId){
            Category oldInfo = categoryRepo.findById(catId).orElseThrow(()->
                new ResourceNotFound("category","categoryId",catId)
            );
            if(null!=oldInfo){
                oldInfo.setCategoryDesc(categoryDto.getCategoryDesc());
                oldInfo.setCategoryTitle(categoryDto.getCategoryTitle());
                save = categoryRepo.save(oldInfo);
            }

        }
        Response response= new Response();
        if(null!=save ){
            response.setMessage("category updated ");
            response.setCode(HttpStatus.OK.value());
            response.setStatus(true);
        }
        return response;
    }


    @Override
    public Response deleteCat(Long catId) {
        if(null!=catId){
            Category oldInfo = categoryRepo.findById(catId).orElseThrow(()->
                    new ResourceNotFound("category","categoryId",catId));
            if(null!=oldInfo) categoryRepo.delete(oldInfo);
        }
        Response response= new Response();

        response.setMessage("category deleted ");
        response.setCode(HttpStatus.OK.value());
        response.setStatus(true);

        return response;
    }

    @Override
    public CategoryDto getCatById(Long catId) {
        CategoryDto categoryDto =null;

            Category oldInfo = categoryRepo.findById(catId).orElseThrow(()->
                    new ResourceNotFound("category","categoryId",catId));
            if(null!=oldInfo) {
                categoryDto = userMapper.categoryToCategoryDto(oldInfo);
            }

        categoryDto.setMessage("category found ");
        categoryDto.setCode(HttpStatus.OK.value());
        categoryDto.setStatus(true);

        return categoryDto;

    }

    @Override
    public AllCategory getAllCat() {
        List<Category> all = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = userMapper.lCatageroDtoToLCategory(all);
        AllCategory allCategory= new AllCategory();
        allCategory.setCategory(categoryDtos);
        allCategory.setCode(HttpStatus.OK.value());
        allCategory.setMessage("all category got");
        allCategory.setStatus(true);
        return allCategory;
    }
}
