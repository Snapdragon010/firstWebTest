package com.github.dragon.service;

import com.github.dragon.bean.Role;

import java.util.List;

public interface RoleService {
    List<Integer> findRoleId(int userId);

    List<Role> findRoleByUserId(int id);

    void add(List<Integer> ids,String userId);
}
