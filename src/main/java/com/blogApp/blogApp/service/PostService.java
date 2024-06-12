package com.blogApp.blogApp.service;

import com.blogApp.blogApp.controller.Response;
import com.blogApp.blogApp.dto.AllPost;
import com.blogApp.blogApp.dto.ImageResponse;
import com.blogApp.blogApp.dto.PostDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PostService {
    public Response createPost(PostDto postDto,Long userId);

    public Response updatePost(PostDto postDto,Long postId,Long userId);

    public Response deletePost(Long postId);

    public AllPost getPostById(Long id);
    public AllPost getAllPost(Integer pageSize,Integer pageNumber);

    public AllPost getAllPostByCat(Long catId,Integer pageNumber,Integer pageSize);

    public AllPost getPostByUser(Long userId,Integer pageSize,Integer pageNumber);

    public ImageResponse imageUpload(MultipartFile image, Long userId, Long postId) throws IOException;


    byte[] downloadImage(String fileName) throws IOException;
}
