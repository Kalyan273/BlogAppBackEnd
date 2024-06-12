package com.blogApp.blogApp.service.serviceImpl;

import com.blogApp.blogApp.controller.Response;
import com.blogApp.blogApp.dto.AllUser;
import com.blogApp.blogApp.dto.RoleDto;
import com.blogApp.blogApp.dto.UserDto;
import com.blogApp.blogApp.entity.Role;
import com.blogApp.blogApp.entity.RoleMapping;
import com.blogApp.blogApp.entity.User;
import com.blogApp.blogApp.exception.ResourceNotFound;
import com.blogApp.blogApp.mapper.UserMapper;
import com.blogApp.blogApp.repo.RoleMappingRepo;
import com.blogApp.blogApp.repo.RoleRepo;
import com.blogApp.blogApp.repo.UserRepo;
import com.blogApp.blogApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMappingRepo roleMappingRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public Response createUser(UserDto userDto) {
        RoleMapping save1 =null;
        Response response= new Response();
        if(null!=userDto){
            User user = userMapper.UserDtoToUser(userDto);
            User save = userRepo.save(user);
            Role role = roleRepo.findById(userDto.getRoleId()).orElseThrow(() ->
                    new ResourceNotFound("role", "roleid", userDto.getRoleId()));

            RoleMapping roleMapping= new RoleMapping();
            roleMapping.setRoleTable(role);
            roleMapping.setUser(save);
            save1 = roleMappingRepo.save(roleMapping);
        }
        if(null!=save1){
            response.setMessage("saved successfully");
            response.setCode(HttpStatus.OK.value());
            response.setStatus(true);
        }
        return response;
    }

    @Override
    public Response updateUser(UserDto userDto, Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("user", "id", id));
        if(null!=user){
            User user1 = userMapper.UserDtoToUser(userDto);
            userRepo.save(user1);
        }
        Response response= new Response();
        response.setMessage("user updated");
        response.setCode(HttpStatus.OK.value());
        response.setStatus(true);
        return response;
    }

    @Override
    public Response deleteUser(Long id) {
        User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("user", "id", id));
        userRepo.delete(user);
        Response response= new Response();
        response.setMessage("user deleted");
        response.setCode(HttpStatus.OK.value());
        response.setStatus(true);
        return response;
    }

    @Override
    public AllUser getAllUser() {
        AllUser allUser= new AllUser();
        List<UserDto>userDtos= new ArrayList<>();
        UserDto userDto1;

        List<RoleMapping> all1 = roleMappingRepo.findAll();
        for (RoleMapping user:all1) {
            userDto1 = userMapper.userToUserDto(user.getUser());
            RoleDto roleDto=new RoleDto();
            roleDto.setId(user.getRoleTable().getId());
            roleDto.setRoleDesc(user.getRoleTable().getRoleDesc());
            roleDto.setRoleGroup(user.getRoleTable().getRoleGroup());
            roleDto.setRole(user.getRoleTable().getRole());

//            RoleDto roleDto = userMapper.roleToRoleDto(user.getRoleTable());
            userDto1.setRoleDto(roleDto);

            userDtos.add(userDto1);
        }
        allUser.setUserDto(userDtos);
        allUser.setMessage("get all user");
        allUser.setCode(HttpStatus.OK.value());
        allUser.setStatus(true);
        allUser.setUserDto(userDtos);
        return allUser;
    }

    @Override
    public AllUser getUserById(Long id) {
        AllUser allUser= new AllUser();
        List<UserDto>userDtos= new ArrayList<>();
        UserDto userDto;
        User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFound("user", "id", id));
        RoleMapping byUserId = roleMappingRepo.findByUserId(id);
        if(null!=user && null!=byUserId){
            userDto = userMapper.userToUserDto(byUserId.getUser());
            RoleDto roleDto=new RoleDto();
            roleDto.setId(byUserId.getRoleTable().getId());
            roleDto.setRoleDesc(byUserId.getRoleTable().getRoleDesc());
            roleDto.setRoleGroup(byUserId.getRoleTable().getRoleGroup());
            roleDto.setRole(byUserId.getRoleTable().getRole());
         //   RoleDto roleDto = userMapper.roleToRoleDto(byUserId.getRoleTable());
            userDto.setRoleDto(roleDto);
            userDtos.add(userDto);
        }
        allUser.setUserDto(userDtos);
        allUser.setMessage("user deleted");
        allUser.setCode(HttpStatus.OK.value());
        allUser.setStatus(true);
        return allUser;
    }
}
