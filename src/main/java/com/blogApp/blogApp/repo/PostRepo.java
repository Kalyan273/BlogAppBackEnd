package com.blogApp.blogApp.repo;

import com.blogApp.blogApp.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Long>, PagingAndSortingRepository<Post, Long> {

    @Query(value = "SELECT e from Post e where e.category.catId=:catId")
    Page<Post> findPostByCat(Long catId, Pageable pageable);


    @Query(value = "SELECT e from Post e where e.user.id=:userId")
    Page<Post> finByUserID(Long userId, Pageable pageable);

    @Query(value = "SELECT p FROM Post p")
    Page<Post> findAllPosts(Pageable pageable);


/*    @Query(value = "select e from post e where e.imageName=:fileName")
    String findByImageName(String fileName);*/
}
