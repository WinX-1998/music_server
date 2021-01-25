package com.example.music.Service;


import com.example.music.Entity.Admin;
import com.example.music.Entity.Permission;
import com.example.music.Entity.Role;

import java.util.Set;

public interface AdminService {
    public boolean addAdmin(Admin admin);
    public Admin findAdminByAdminName(String adminName);
    public Set<String> findRolesByAdminName(String adminName);
    public Set<String>findPermissionsByAdminName(String adminName);
}
