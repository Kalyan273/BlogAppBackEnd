package com.blogApp.blogApp.dto;


import com.blogApp.blogApp.controller.Response;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long userId;
    private String name;
    private String email;
    private String password;
    private String about;
    private Long roleId;
    private RoleDto roleDto;
}
