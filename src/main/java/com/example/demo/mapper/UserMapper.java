package com.example.demo.mapper;

import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

    @Select("select user_pwd as userPwd, " +
            "user_email as userEmail, " +
            "user_num as userNum " +
            "from demo_user_info where user_name = #{username}")
    UserEntity selectByUserName(@Param("username") String userName);
}
