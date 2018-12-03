package com.itheima.service.impl;

import com.itheima.dao.IPermissionDao;
import com.itheima.domain.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll(int page, int size) {
        return permissionDao.findAll(page,size);
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    @Override
    public Permission findById(String id) {
        return permissionDao.findByPermissionId(id);
    }

    @Override
    public void updateByPermissionId(Permission permission) {
        permissionDao.updateByPermissionId(permission);
    }

    @Override
    public void deleteByPermissionId(String id) {
        permissionDao.deleteByPermissionId(id);
        permissionDao.deleteById(id);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id : ids) {
            permissionDao.deleteByPermissionId(id);
            permissionDao.deleteById(id);
        }
    }

}
