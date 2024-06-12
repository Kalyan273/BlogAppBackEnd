package com.blogApp.blogApp.dto;

import com.blogApp.blogApp.controller.Response;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long postId;
    private String title;
    private String content;
    private String imageName;
    private Boolean isLive;
    private Long catId;

}
