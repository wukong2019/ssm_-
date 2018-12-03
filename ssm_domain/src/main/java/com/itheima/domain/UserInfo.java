package com.itheima.domain;

import lombok.Data;

import java.util.List;

@Data
public class UserInfo {
    private String id;
    private String username;
    private String email;
    private String password;
    private String phoneNum;
    private Integer status;
    private String statusStr;
    private List<Role> roles;

    public String getStatusStr() {
        if(status!=null){
            if(status==0){
                statusStr="关闭";
            }
            if(status==1){
                statusStr="开启";
            }

        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }
}
