package com.blogApp.blogApp.service;


import com.blogApp.blogApp.controller.Response;
import com.blogApp.blogApp.dto.AllRole;
import com.blogApp.blogApp.dto.RoleDto;

public interface RoleService {


    public Response addRole(RoleDto roleDto);

    public Response updateRole(RoleDto roleDto,Long roleID);
    public Response deleteRole(Long roleID);
    public AllRole allRole();
}
