package com.blogApp.blogApp.controller;



import com.blogApp.blogApp.dto.AllPost;
import com.blogApp.blogApp.dto.ImageResponse;
import com.blogApp.blogApp.dto.PostDto;
import com.blogApp.blogApp.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/addPost")
    public ResponseEntity<Response> createCategory(@Valid @RequestBody PostDto postDto, @RequestHeader Long userId) throws Exception {

        if(null!=postDto){
            Response response = postService.createPost(postDto,userId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else throw new Exception("invalid data");

    }
    @PostMapping("/updatePost/{id}")
    public ResponseEntity<Response> updateUser(@Valid @RequestBody PostDto postDto, @PathVariable Long id,@RequestHeader Long userId) throws Exception {

        if(null!=postDto){
            Response response = postService.updatePost(postDto,id,userId);
            return ResponseEntity.ok(response);
        }else throw new Exception("invalid data");

    }
    @PostMapping("/deletePost/{id}")
    public ResponseEntity<Response> deleteUser(@RequestHeader Long id) throws Exception {


            Response response = postService.deletePost(id);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);


    }
    @PostMapping("/allPost")
    public ResponseEntity<AllPost> allPost(@RequestParam Integer pageSize,@RequestParam  Integer pageNumber) throws Exception {


        AllPost allCategory = postService.getAllPost(pageSize,pageNumber);
        return new ResponseEntity<>(allCategory,HttpStatus.ACCEPTED);

    }
    @PostMapping("/postByCatId")
    public ResponseEntity<Response> getAllPostByCat(@RequestHeader("id") long id,@RequestParam Integer pageNumber,@RequestParam Integer pageSize) throws Exception {


            Response response = postService.getAllPostByCat(id, pageNumber,pageSize);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);


    }

    @PostMapping("/postByUserId/{id}")
    public ResponseEntity<Response> getPostByUser(@PathVariable Long id,@RequestParam Integer pageSize,@RequestParam Integer pageNumber) throws Exception {


            Response response = postService.getPostByUser(id,pageSize,pageNumber);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);


    }

    @PostMapping("/postById/{id}")
    public ResponseEntity<AllPost> getPostById(@RequestHeader Long id) throws Exception {


            AllPost response = postService.getPostById(id);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);


    }

    @PostMapping("/upload")
    public ResponseEntity<ImageResponse> uploadImage(@RequestParam ("file") MultipartFile file,@RequestParam Long userId,@RequestParam Long postId) throws IOException {

        ImageResponse imageResponse = postService.imageUpload(file, userId, postId);
        return new ResponseEntity<>(imageResponse,HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/download",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> downloadImage(@RequestParam String fileName) throws IOException {

        byte[] imageResponse = postService.downloadImage(fileName);
        return new ResponseEntity<>(imageResponse,HttpStatus.ACCEPTED);
    }
}
