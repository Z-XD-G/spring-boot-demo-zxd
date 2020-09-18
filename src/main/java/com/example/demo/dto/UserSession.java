package com.example.demo.dto;

import com.example.demo.annotation.NotEmpty;
import com.example.demo.annotation.NotNull;

import java.io.Serializable;

public class UserSession implements Serializable {

    private String userName;
    @NotNull(message = "用户编号未输入")
    @NotEmpty(messsage = "用户编号为空")
    private String userNum;
    private String userEmail;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                ", userNum='" + userNum + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
