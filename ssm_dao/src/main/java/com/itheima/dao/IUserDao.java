package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface IUserDao {

    //@Select("select * from role where id in(select roleid from users_role where userid in(select id from USERS where username=#{username}))")



    @Select("select * from users where username=#{username}")
    @Results(value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many =@Many(select = "com.itheima.dao.IRoleDao.findByUserId")),
    })
    UserInfo findByUsername(String username);


    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users (email,username,password,phonenum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);


    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.IRoleDao.findByUserId")),
    })
    UserInfo findById(String id);


    @Select("select * from role where id not in (select roleid from users_role where userid=#{id})")
    List<Role> findOtherRoles(String id);


    @Insert("insert into users_role (userid,roleid) values(#{userId},#{rolesId})")
    void addRoleToUser(@Param("userId") String userId,@Param("rolesId") String rolesId);


    @Select("select * from users where id in (select userid from users_role where roleid=#{roleid})")
    List<UserInfo> findByRoleId(String roleid);
}
