package com.github.dragon.dao;

import com.github.dragon.bean.Role;
import com.github.dragon.bean.UserRole;

import java.util.List;

public interface RoleDao {
    List<Integer> findRoleIdByUserId(int userId);//查找roleID

    List<Role> findRoleByUserId(int idd);//查找对应角色信息

    void addRole(UserRole userRole);
}
