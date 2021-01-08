package com.example.music.Service.Impl;

import com.example.music.Entity.Admin;
import com.example.music.Mapper.AdminMapper;
import com.example.music.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findAdminByAdminName(String adminName) {
       return adminMapper.findByAdminrname(adminName);
    }

}
