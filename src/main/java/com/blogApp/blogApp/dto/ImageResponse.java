package com.blogApp.blogApp.dto;

import com.blogApp.blogApp.controller.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse extends Response {

    String imageName;
}
