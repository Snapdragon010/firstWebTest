package com.github.dragon.service.impl;

import com.github.dragon.bean.Role;
import com.github.dragon.bean.UserRole;
import com.github.dragon.dao.RoleDao;
import com.github.dragon.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl  implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Integer> findRoleId(int userId) {
        return roleDao.findRoleIdByUserId(userId);
    }

    @Override
    public List<Role> findRoleByUserId(int id) {
        return roleDao.findRoleByUserId(id);
    }

    @Override
    public void add(List<Integer> ids, String userId) {
        for (int roleId:ids){
            UserRole userRole=new UserRole();
            userRole.setUserId(Integer.parseInt(userId));
            userRole.setRoleId(roleId);
            roleDao.addRole(userRole);
        }
    }
}
