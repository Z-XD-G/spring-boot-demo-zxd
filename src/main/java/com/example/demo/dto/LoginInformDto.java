package com.example.demo.dto;

import com.example.demo.annotation.NotEmpty;
import com.example.demo.annotation.NotNull;

import java.io.Serializable;

public class LoginInformDto implements Serializable {

    @NotNull(message = "用户名未输入")
    @NotEmpty(messsage = "用户名为空")
    private String userName;
    @NotNull(message = "用户密码未输入")
    @NotEmpty(messsage = "用户密码为空")
    private String userPwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Override
    public String toString() {
        return "LoginInformDto{" +
                "userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }
}
