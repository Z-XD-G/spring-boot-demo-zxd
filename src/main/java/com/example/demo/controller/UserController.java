package com.example.demo.controller;

import com.example.demo.dto.LoginInformDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Map<String, UserDto> login(@RequestBody LoginInformDto loginInfo){
        return userService.loginCheck(loginInfo);
    }
}
