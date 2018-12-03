package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.IUserDao;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username)  {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),true,true,true,userInfo.getStatus()==0?false:true,getAuthority(userInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
    List<SimpleGrantedAuthority> list=new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName().toUpperCase()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return userDao.findAll();
    }


    @Override
    public void save(UserInfo userInfo) {
        userDao.save(userInfo);
    }


    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id);
    }


    @Override
    public List<Role> findOtherRoles(String id) {
        return userDao.findOtherRoles(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] rolesId) {
        for (String roleid : rolesId) {
            userDao.addRoleToUser(userId,roleid);
        }
    }

//    @Override
//    public UserInfo findById(String id,int page,int size) {
//        PageHelper.startPage(page,size);
//        return userDao.findById(id,page,size);
//    }
}
