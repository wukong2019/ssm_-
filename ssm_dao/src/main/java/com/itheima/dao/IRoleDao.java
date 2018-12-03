package com.itheima.dao;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IRoleDao {

//    @Results({
//            @Result(id = true,property = "id",column = "id"),
//            @Result(property = "roleName",column = "roleName"),
//            @Result(property = "roleDesc",column = "roleDesc"),
//            @Result(property = "permissions",column = "id",javaType = Permission.class,many = @Many(select = "com.itheima.dao.IPermissionDao.findById")),
//            @Result(property = "users",column = "id",javaType = UserInfo.class,many = @Many(select = "com.itheima.dao.IUserDao.findByRoleId")),
//    })

    @Select("select * from role where id in(select roleid from users_role where userid =#{id})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.IPermissionDao.findById")),
            //@Result(property = "users",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.IUserDao.findByRoleId")),
    })
    List<Role> findByUserId(String id);

    @Select("select * from role")
    List<Role> findAll(int page,int size);


    @Insert("insert into role (rolename,roledesc) values(#{roleName},#{roleDesc})")
    void save(Role role);


    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.IPermissionDao.findById")),
            //@Result(property = "users",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.IUserDao.findByRoleId")),
    })
    Role findById(String id);


    @Select("select * from permission where id not in (select permissionid from role_permission where roleid=#{roleId})")
    List<Permission> findRoleByIdAndAllPermission(String roleId);


    @Insert("insert into role_permission (permissionid,roleid)  values(#{permissionid},#{roleId})")
    void addPermissionToRole(@Param("permissionid") String permissionid,@Param("roleId") String roleId);
}
