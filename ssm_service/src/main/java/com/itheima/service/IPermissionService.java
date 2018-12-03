package com.itheima.service;

import com.itheima.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll(int page,int size);

    void save(Permission permission);

    Permission findById(String id);

    void updateByPermissionId(Permission permission);

    void deleteByPermissionId(String id);

    void deleteByIds(String[] ids);
}
