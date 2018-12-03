package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission")
    List<Permission> findAll(int page,int size);

    @Insert("insert into permission (permissionname,url) values (#{permissionName},#{url})")
    void save(Permission permission);

    @Select("select * from permission where id in(select permissionid from role_permission where roleid=#{roleid})")
    List<Permission> findById(String roleid);



    @Select("select * from permission where id in(select permissionid from role_permission where roleid in(select roleid from users_role where userid=#{id})) ")
    List<Permission> findByUserId(String id);


    @Select("select * from permission where id=#{id}")
    Permission findByPermissionId(String id);


    @Update("update permission set permissionname=#{permissionName},url=#{url} where id=#{id} ")
    void updateByPermissionId(Permission permission);

    @Delete("delete from role_permission where permissionid =#{id}")
    void deleteByPermissionId(String id);

    @Delete("delete from  permission where id=#{id}")
    void deleteById(String id);
}
