package com.example.music.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.music.Entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    @Select("select * from admin where admin_name = #{adminName}")
    public Admin findByAdminrname(String adminName);

    @Select("select distinct r.code" +
            " from role r,admin u,admin_role ur" +
            " where r.id = ur.role_id and u.id = ur.admin_id and u.admin_name = #{adminName}")
    Set<String> findRolesByAdminName(String adminName);

    @Select("select distinct p.code " +
            "from role r,admin u,admin_role ur,role_perm rp,permission p " +
            "where r.id = ur.role_id and u.id = ur.admin_id and rp.role_id = r.id and rp.perm_id = p.id " +
            "and u.admin_name = #{adminName}")
    Set<String> findPermissionsByAdminName(String adminName);
}
