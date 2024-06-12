package com.blogApp.blogApp.repo;

import com.blogApp.blogApp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    Category findByCatId(Long catId);


}
