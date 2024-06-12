package com.blogApp.blogApp.repo;

import com.blogApp.blogApp.entity.Category;
import com.blogApp.blogApp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {


}
