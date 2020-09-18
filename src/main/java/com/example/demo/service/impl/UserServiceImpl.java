package com.example.demo.service.impl;

import com.example.demo.annotation.AnnotationValid;
import com.example.demo.contect.RedisContect;
import com.example.demo.dto.LoginInformDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserSession;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    //获取Redis连接
    private Jedis jedisInstance = RedisContect.getInstance().getJedisInstance();
    //启动注解校验实例
    private AnnotationValid annotationValid = new AnnotationValid();

    /**
     * 登录校验并将登录信息存入Redis
     * @param loginInfo
     * @return
     */
    @Override
    public Map<String, Object> loginCheck(LoginInformDto loginInfo) {

        Map<String, Object> msgMap = new HashMap();
        String message = annotationValid.validate(loginInfo);
        if ("0".equals(annotationValid.validate(loginInfo))){
            UserEntity userEntity = userMapper.selectByUserName(loginInfo.getUserName());
            if (userEntity == null){
                msgMap.put("message", "user not exist");
            }else{
                if(userEntity.getUserPwd().equals(loginInfo.getUserPwd())){
                    UserDto userDto = new UserDto();
                    userDto.setUserEmail(userEntity.getUserEmail());
                    userDto.setUserNum(userEntity.getUserNum());
                    userDto.setUserName(loginInfo.getUserName());
                    msgMap.put("message", "success");
                    msgMap.put("user", userDto);
                    jedisInstance.set(userEntity.getUserNum(), "online");
                    jedisInstance.expire(userEntity.getUserNum(), 60*24*30);
                }else{
                    msgMap.put("message", "username or password error");
                }
            }
        }else {
            msgMap.put("message", message);
        }
        return msgMap;
    }

    /**
     * 校验Redis中是否存在登录信息
     * @param userSession
     * @return
     */
    @Override
    public Map<String, Object> onlineCheck(UserSession userSession) {
        Map<String, Object> msgMap = new HashMap();
        String message = annotationValid.validate(userSession);
        if ("0".equals(annotationValid.validate(userSession))) {
            if (jedisInstance.exists(userSession.getUserNum())) {
                msgMap.put(userSession.getUserNum(), jedisInstance.get(userSession.getUserNum()));
            } else {
                msgMap.put(userSession.getUserNum(), "offline");
            }
        }else{
            msgMap.put("message", message);
        }
        return msgMap;
    }

    /**
     * 注销登录时删除Redis数据
     * @param userSession
     */
    @Override
    public void logOff(UserSession userSession) {
        jedisInstance.del(userSession.getUserNum());
    }
}
