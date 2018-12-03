package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.IRoleDao;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return roleDao.findAll(page,size);
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public List<Permission> findRoleByIdAndAllPermission(String roleId) {
        return roleDao.findRoleByIdAndAllPermission(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionId) {
        for (String permissionid : permissionId) {
            roleDao.addPermissionToRole(permissionid,roleId);
        }
    }
}
