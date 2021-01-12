package com.sheng.dao;

import com.sheng.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    //获取角色列表
    public List<Role> getRoleList();

    //添加角色
    public int addRole(Role role);

    //根据角色名查询角色
    public Role queryRoleByName(@Param("roleName") String roleName);

    //更具角色名删除角色
    public int deleteRoleByName(@Param("roleName") String roleName);
}
