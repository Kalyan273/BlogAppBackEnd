package com.blogApp.blogApp.controller;


import com.blogApp.blogApp.dto.AllCategory;
import com.blogApp.blogApp.dto.AllRole;
import com.blogApp.blogApp.dto.CategoryDto;
import com.blogApp.blogApp.dto.RoleDto;
import com.blogApp.blogApp.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {


    @Autowired
    private RoleService roleService;


    @PostMapping("/addRole")
    public ResponseEntity<Response> allRole(@Valid @RequestBody RoleDto roleDto) throws Exception {

        if(null!=roleDto){
            Response response = roleService.addRole(roleDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }else throw new Exception("invalid data");

    }
    @PostMapping("/updateRole/{id}")
    public ResponseEntity<Response> updateRole(@Valid @RequestBody RoleDto roleDto, @PathVariable Long id) throws Exception {

        if(null!=roleDto || null!=id){
            Response response = roleService.updateRole(roleDto,id);
            return ResponseEntity.ok(response);
        }else throw new Exception("invalid data");

    }
    @PostMapping("/deleteRole/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable Long id) throws Exception {

        if(null!=id){
            Response response = roleService.deleteRole(id);
            return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
        }else throw new Exception("not found");

    }
    @PostMapping("/allRole")
    public ResponseEntity<AllRole> allRole() throws Exception {


        AllRole allRole = roleService.allRole();
        return new ResponseEntity<>(allRole,HttpStatus.ACCEPTED);

    }
}
