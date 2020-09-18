package com.example.demo.controller;

import com.example.demo.annotation.AnnotationValid;
import com.example.demo.dto.LoginInformDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserSession;
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

    /**
     * 登录
     * @param loginInfo
     * @return
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginInformDto loginInfo){
        return userService.loginCheck(loginInfo);
    }

    /**
     * 校验缓存中是否存在登录信息
     * @param userSession
     * @return
     */
    @PostMapping("/onlineCheck")
    public Map<String, Object> checkLogin(@RequestBody UserSession userSession){
        return userService.onlineCheck(userSession);
    }

    /**
     * 注销登录
     * @param userSession
     * @return
     */
    @PostMapping("/logout")
    public String logout(@RequestBody UserSession userSession){
        userService.logOff(userSession);
        return "success";
    }
}
