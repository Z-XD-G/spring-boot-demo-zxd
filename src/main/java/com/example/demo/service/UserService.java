package com.example.demo.service;

import com.example.demo.dto.LoginInformDto;
import com.example.demo.dto.UserDto;

import java.util.Map;

public interface UserService {

    Map<String, UserDto> loginCheck(LoginInformDto loginInfo);

}
