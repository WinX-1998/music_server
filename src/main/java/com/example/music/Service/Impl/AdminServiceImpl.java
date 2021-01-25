package com.example.music.Service.Impl;

import com.example.music.Entity.Admin;
import com.example.music.Entity.Permission;
import com.example.music.Entity.Role;
import com.example.music.Mapper.AdminMapper;
import com.example.music.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findAdminByAdminName(String adminName) {
       return adminMapper.findByAdminrname(adminName);
    }

    @Override
    public boolean addAdmin(Admin admin){
        int insert = adminMapper.insert(admin);
        if(insert!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Set<String> findRolesByAdminName(String adminName) {
        Set<String> rolesByAdminName = adminMapper.findRolesByAdminName(adminName);
        return rolesByAdminName;
    }

    @Override
    public Set<String> findPermissionsByAdminName(String adminName) {
        Set<String> permissionsByAdminName = adminMapper.findPermissionsByAdminName(adminName);
        return permissionsByAdminName;
    }

}
