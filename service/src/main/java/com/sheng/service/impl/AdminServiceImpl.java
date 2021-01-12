package com.sheng.service.impl;

import com.sheng.dao.AdminsMapper;
import com.sheng.service.AdminService;
import com.sheng.vo.admin.AdminUser;
import com.sheng.vo.admin.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminsMapper adminsMapper;

    @Override
    public AdminVo queryByName(AdminUser adminUser) {
        return adminsMapper.queryByName(adminUser);
    }
}
