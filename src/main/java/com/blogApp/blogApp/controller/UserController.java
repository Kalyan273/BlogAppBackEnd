package com.blogApp.blogApp.controller;


import com.blogApp.blogApp.dto.AllUser;
import com.blogApp.blogApp.dto.UserDto;
import com.blogApp.blogApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<Response> createUser(@Valid @RequestBody UserDto userDto) throws Exception {

        if(null!=userDto){
            Response response = userService.createUser(userDto);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }else throw new Exception("invalid data");

    }
    @PostMapping("/updateUser/{id}")
    public ResponseEntity<Response> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long id) throws Exception {

        if(null!=userDto || null!=id){
            Response response = userService.updateUser(userDto,id);
            return ResponseEntity.ok(response);
        }else throw new Exception("invalid data");

    }
    @PostMapping("/deleteUser/{id}")
    public ResponseEntity<Response> deleteUser(Long id) throws Exception {

        if(null!=id){
            Response response = userService.deleteUser(id);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        }else throw new Exception("not found");

    }
    @PostMapping("/allUser")
    public ResponseEntity<AllUser> allUser() throws Exception {


        AllUser allUser = userService.getAllUser();
            return new ResponseEntity<>(allUser,HttpStatus.ACCEPTED);

    }
    @PostMapping("/byUserId/{id}")
    public ResponseEntity<AllUser> userById(Long id) throws Exception {

        if(null!=id){
            AllUser response = userService.getUserById(id);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        }else throw new Exception("invalid data");

    }
}
