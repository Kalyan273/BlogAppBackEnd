package com.blogApp.blogApp.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is used as return type for every API
 */

@Getter
@Setter
@NoArgsConstructor

public class Response {

    private Integer code;
    private String message;
    private Boolean status;


    public Response(int value, String roleCreated, boolean status) {
       this.code=value;
       this.message=roleCreated;
       this.status=status;
    }
}
