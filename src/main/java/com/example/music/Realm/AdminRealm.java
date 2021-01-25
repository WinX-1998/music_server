package com.example.music.Realm;


import com.example.music.Entity.Admin;
import com.example.music.Entity.User;
import com.example.music.Service.AdminService;
import com.example.music.Service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class AdminRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("管理员正在授权");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String adminName = (String) principalCollection.getPrimaryPrincipal();

        //从数据库查询对象
        Admin adminQuery = adminService.findAdminByAdminName(adminName);
        if(adminQuery==null){
            return null;
        }else{
            Set<String> permissionsByAdminName = adminService.findPermissionsByAdminName(adminName);
            simpleAuthorizationInfo.addStringPermissions(permissionsByAdminName);
            Set<String> rolesByAdminName = adminService.findRolesByAdminName(adminName);
            simpleAuthorizationInfo.addRoles(rolesByAdminName);
            return simpleAuthorizationInfo;
        }
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("管理员正在认证登录..");
        String adminName = authenticationToken.getPrincipal().toString();
        Admin admin = adminService.findAdminByAdminName(adminName);
        String password = admin.getPassword();
        String salt = admin.getSalt();
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(adminName, password, ByteSource.Util.bytes(salt), getName());
        return simpleAuthenticationInfo;
    }
}
