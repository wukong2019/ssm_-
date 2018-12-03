package com.itheima.service;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {


    List<UserInfo> findAll(int page,int size);

    void save(UserInfo userInfo);

    //UserInfo findById(String id,int page,int size);
    UserInfo findById(String id);

    List<Role> findOtherRoles(String id);

    void addRoleToUser(String userId, String[] rolesId);
}
