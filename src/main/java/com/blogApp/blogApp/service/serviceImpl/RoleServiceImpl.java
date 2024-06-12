package com.blogApp.blogApp.service.serviceImpl;

import com.blogApp.blogApp.controller.Response;
import com.blogApp.blogApp.dto.AllRole;
import com.blogApp.blogApp.dto.RoleDto;
import com.blogApp.blogApp.entity.Role;
import com.blogApp.blogApp.exception.ResourceNotFound;
import com.blogApp.blogApp.mapper.UserMapper;
import com.blogApp.blogApp.repo.RoleRepo;
import com.blogApp.blogApp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepo repo;

    @Autowired
    private UserMapper userMapper;
    @Override
    public Response addRole(RoleDto roleDto) {

        if(null!=roleDto){
            Role role = userMapper.roleDtoToRole(roleDto);
            repo.save(role);
        }

        Response response= new Response();
        response.setStatus(true);
        response.setMessage("role saved");
        response.setCode(HttpStatus.OK.value());
        return response;
    }

    @Override
    public Response updateRole(RoleDto roleDto, Long roleID) {
        Role role = repo.findById(roleID).orElseThrow(() ->
                new ResourceNotFound("Role", "RoleId", roleID));
        if(null!=role){
            role.setRoleDesc(roleDto.getRoleDesc());
            role.setRoleGroup(roleDto.getRoleGroup());
            role.setRole(roleDto.getRole());
            repo.save(role);
        }
        Response response= new Response();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("role updated");
        response.setStatus(true);
        return response;
    }

    @Override
    public Response deleteRole(Long roleID) {
        Role role = repo.findById(roleID).orElseThrow(() ->
                new ResourceNotFound("Role", "RoleId", roleID));

        if(null!=role){
            repo.delete(role);

        }
        Response response= new Response();
        response.setCode(HttpStatus.OK.value());
        response.setMessage("role deleted");
        response.setStatus(true);
        return response;
    }

    @Override
    public AllRole allRole() {
        AllRole allRole= new AllRole();
        List<Role> all = repo.findAll();
        if(!all.isEmpty()){
            List<RoleDto> roleDtos = userMapper.lRoleDtoToLRole(all);
            allRole.setRoleList(roleDtos);
        }
        allRole.setCode(HttpStatus.OK.value());
        allRole.setMessage("get role");
        allRole.setStatus(true);
        return allRole;
    }
}
