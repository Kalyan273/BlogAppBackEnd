package com.blogApp.blogApp.service;

import com.blogApp.blogApp.controller.Response;
import com.blogApp.blogApp.dto.AllUser;
import com.blogApp.blogApp.dto.UserDto;

import java.util.List;

public interface UserService {

    Response createUser(UserDto userDto);

    Response updateUser(UserDto userDto,Long id);
    Response deleteUser(Long id);

    AllUser getAllUser();
    AllUser getUserById(Long id);

}
