package com.itheima.service;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll(int page,int size);

    Role findById(String id);

    void save(Role role);

    List<Permission> findRoleByIdAndAllPermission(String roleId);

    void addPermissionToRole(String roleId, String[] permissionId);
}
