package com.blogApp.blogApp.mapper;


import com.blogApp.blogApp.dto.CategoryDto;
import com.blogApp.blogApp.dto.PostDto;
import com.blogApp.blogApp.dto.RoleDto;
import com.blogApp.blogApp.dto.UserDto;
import com.blogApp.blogApp.entity.Category;
import com.blogApp.blogApp.entity.Post;
import com.blogApp.blogApp.entity.Role;
import com.blogApp.blogApp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


    User UserDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);

    List<UserDto>listUserToLUserDto(List<User> userList);


    @Mapping(target = "catId",ignore = true)
    Category categoryDtoToCategory(CategoryDto categoryDto);

    CategoryDto  categoryToCategoryDto(Category category);

    List<CategoryDto> lCatageroDtoToLCategory(List<Category> all);

    @Mapping(target = "postId",ignore = true)
    Post postDtoToPost(PostDto postDto);
    PostDto postToPostDto(Post post);

    List<PostDto> lPostToLPostDto(List<Post> all);

    @Mapping(target = "id",ignore = true)
    Role roleDtoToRole(RoleDto roleDto);
    List<RoleDto> lRoleDtoToLRole(List<Role>roleList);

    RoleDto roleToRoleDto(Role role);
}
