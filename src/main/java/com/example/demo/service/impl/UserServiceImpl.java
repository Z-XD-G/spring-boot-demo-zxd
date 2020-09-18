package com.example.demo.service.impl;

import com.example.demo.dto.LoginInformDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Map<String, UserDto> loginCheck(LoginInformDto loginInfo) {
        UserEntity userEntity = userMapper.selectByUserName(loginInfo.getUserName());
        Map<String, UserDto> msgMap = new HashMap();
        if(userEntity==null){
            msgMap.put("user not exist", null);
        }else{
            if(userEntity.getUserPwd().equals(loginInfo.getUserPwd())){
                UserDto userDto = new UserDto();
                userDto.setUserEmail(userEntity.getUserEmail());
                userDto.setUserNum(userEntity.getUserNum());
                userDto.setUserName(loginInfo.getUserName());
                msgMap.put("success", userDto);
            }else{
                msgMap.put("username or password error", null);
            }
        }
        return msgMap;
    }
}
