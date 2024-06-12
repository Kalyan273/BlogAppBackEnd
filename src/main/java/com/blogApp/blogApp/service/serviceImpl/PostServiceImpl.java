package com.blogApp.blogApp.service.serviceImpl;

import com.blogApp.blogApp.controller.Response;
import com.blogApp.blogApp.dto.AllPost;
import com.blogApp.blogApp.dto.ImageResponse;
import com.blogApp.blogApp.dto.PostDto;
import com.blogApp.blogApp.entity.Category;
import com.blogApp.blogApp.entity.Post;
import com.blogApp.blogApp.entity.User;
import com.blogApp.blogApp.exception.ResourceNotFound;
import com.blogApp.blogApp.mapper.UserMapper;
import com.blogApp.blogApp.repo.CategoryRepo;
import com.blogApp.blogApp.repo.PostRepo;
import com.blogApp.blogApp.repo.UserRepo;
import com.blogApp.blogApp.service.PostService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class PostServiceImpl implements PostService {


    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Response createPost(PostDto postDto,Long userId) {
        Post post = userMapper.postDtoToPost(postDto);
        LocalDateTime now = LocalDateTime.now();
        post.setAddedDate(now);
        Category category1 = categoryRepo.findById(postDto.getCatId()).orElseThrow(() ->
                new ResourceNotFound("category", "categoryId", postDto.getCatId())
        );
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("user", "userId", userId));
        if(null!=category1 && null!=user){
            post.setCategory(category1);
            post.setUser(user);
        }
        postRepo.save(post);
        Response response=new Response();

        response.setMessage("Post saved successfully");
        response.setCode(HttpStatus.OK.value());
        response.setStatus(true);
        return response;
    }

    @Override
    public Response updatePost(PostDto postDto, Long postId,Long userId) {
        Response response=new Response();
        Post byId = postRepo.findById(postId).orElseThrow(()->
                new ResourceNotFound("post","postId",postId)
        );
        Category category1 = categoryRepo.findById(postDto.getCatId()).orElseThrow(() ->
                new ResourceNotFound("category", "categoryId", postDto.getCatId())
        );
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("user", "userId", userId));

        if(null!=byId && null!=category1 && null!=user){
            byId.setTitle(postDto.getTitle());
            byId.setContent(postDto.getContent());
            byId.setAddedDate(byId.getAddedDate());
            byId.setCategory(category1);
            byId.setUser(user);
            byId.setImageName(postDto.getImageName());
            byId.setIsLive(postDto.getIsLive());
            postRepo.save(byId);
        }
        response.setMessage("Post updated successfully");
        response.setCode(HttpStatus.OK.value());
        response.setStatus(true);
        return response;
    }

    @Override
    public Response deletePost(Long postId) {
        Response response=new Response();
        if(null!=postId){
            Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("post", "postId", postId));
            postRepo.delete(post);

        }
        response.setMessage("Post deleted successfully");
        response.setCode(HttpStatus.OK.value());
        response.setStatus(true);
        return response;
    }

    @Override
    public AllPost getPostById(Long id) {
        AllPost allPost= new AllPost();
        PostDto postDto;
        List<PostDto> postDtos= new ArrayList<>();

        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFound("post", "postId", id));
        if(null!=post){
            postDto = userMapper.postToPostDto(post);
            postDtos.add(postDto);
        }
        allPost.setPostDto(postDtos);
        allPost.setCode(HttpStatus.OK.value());
        allPost.setMessage("post get successfully");
        allPost.setStatus(true);
        return allPost;
    }

    @Override
    public AllPost getAllPost(Integer pageSize,Integer pageNumber) {
        AllPost allPost= new AllPost();
        Pageable pageable= PageRequest.of(pageNumber,pageSize);
        Page<Post> all = postRepo.findAll(pageable);
        List<Post> posts = all.toList();

        if(all.getTotalElements()!=0){
            List<PostDto> postDtos = userMapper.lPostToLPostDto(posts);
            allPost.setPostDto(postDtos);
            allPost.setTotalRecord(all.getTotalPages());
            allPost.setTotalSize(all.getSize());
        }
        allPost.setCode(HttpStatus.OK.value());
        allPost.setMessage("post get successfully");
        allPost.setStatus(true);
        return allPost;
    }

    @Override
    public AllPost getAllPostByCat(Long catId, Integer pageNumber, Integer pageSize) {
        AllPost allPost= new AllPost();
        Pageable pageable= PageRequest.of(pageNumber,pageSize, Sort.by("addedDate").descending());
        Page<Post> postByCat = this.postRepo.findPostByCat(catId,pageable);


        if(postByCat.isEmpty()){
            List<PostDto> postDtos = userMapper.lPostToLPostDto(postByCat.toList());
            allPost.setPostDto(postDtos);
        }
        allPost.setCode(HttpStatus.OK.value());
        allPost.setMessage("post get successfully");
        allPost.setStatus(true);
        return allPost;
    }

    @Override
    public AllPost getPostByUser(Long userId,Integer pageSize,Integer pageNumber) {
        AllPost allPost= new AllPost();
        Pageable pageable= PageRequest.of(pageNumber,pageSize, Sort.by("addedDate").descending());
        Page<Post> posts = postRepo.finByUserID(userId,pageable);
        if(posts.getTotalElements()!=0){
            List<PostDto> postDtos = userMapper.lPostToLPostDto(posts.toList());
            allPost.setPostDto(postDtos);
        }
        allPost.setCode(HttpStatus.OK.value());
        allPost.setMessage("post get successfully");
        allPost.setStatus(true);
        return allPost;
    }

    @Override
    public ImageResponse imageUpload( MultipartFile image, Long userId, Long postId) throws IOException {
        String directory="D:/blogApp/blogApp/src/main/resources/";
        String filename ="";
        if(null!=image){
            String extension = FilenameUtils.getExtension(image.getOriginalFilename());
            filename = UUID.randomUUID() + "." + extension;
            Path fileNameAndPath = Paths.get(directory, filename);
            Files.write(fileNameAndPath, image.getBytes());
        }
        ImageResponse imageResponse= new ImageResponse();
        imageResponse.setStatus(true);
        imageResponse.setCode(HttpStatus.OK.value());
        imageResponse.setMessage("image name");
        imageResponse.setImageName(filename);


        return imageResponse;
    }

    @Override
    public byte[] downloadImage(String fileName) throws IOException {
        String directory = "D:/blogApp/blogApp/src/main/resources/";
        File file = new File(directory, fileName);

        if (!file.exists()) {
            return null;
        }
            return Files.readAllBytes(Paths.get(directory, fileName));
    }
}
