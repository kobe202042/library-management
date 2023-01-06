package com.example.springboot.controller;

import com.example.springboot.controller.request.BaseRequest;
import lombok.Data;

@Data
public class adminPageRequest extends BaseRequest {
    private String username;
    private String phone;
    private String email;

}
