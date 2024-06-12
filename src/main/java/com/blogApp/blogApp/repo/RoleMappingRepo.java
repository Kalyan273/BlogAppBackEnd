package com.blogApp.blogApp.repo;


import com.blogApp.blogApp.entity.RoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMappingRepo extends JpaRepository<RoleMapping,Long> {


    @Query(value = "select e from RoleMapping e where e.user.userId=:id")
    RoleMapping findByUserId(Long id);
}
