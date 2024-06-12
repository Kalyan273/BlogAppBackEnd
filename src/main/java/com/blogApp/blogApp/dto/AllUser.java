package com.blogApp.blogApp.dto;

import com.blogApp.blogApp.controller.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllUser extends Response {

    List<UserDto>userDto;
}
