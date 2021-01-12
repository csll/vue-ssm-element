package com.sheng.service.impl;

import com.sheng.dao.RoleMapper;
import com.sheng.pojo.Role;
import com.sheng.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }

    @Override
    public int addRole(Role role) {
        return roleMapper.addRole(role);
    }

    @Override
    public Role queryRoleByName(String roleName) {
        return roleMapper.queryRoleByName(roleName);
    }

    @Override
    public int deleteRoleByName(String roleName) {
        return roleMapper.deleteRoleByName(roleName);
    }
}
