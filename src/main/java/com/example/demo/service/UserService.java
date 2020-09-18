package com.example.demo.service;

import com.example.demo.dto.LoginInformDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserSession;

import java.util.Map;

public interface UserService {

    Map<String, Object> loginCheck(LoginInformDto loginInfo);

    Map<String, Object> onlineCheck(UserSession userSession);

    void logOff(UserSession userSession);
}
