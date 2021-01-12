package com.sheng.service;


import com.sheng.vo.admin.AdminUser;
import com.sheng.vo.admin.AdminVo;

public interface AdminService {
    AdminVo queryByName(AdminUser adminUser);
}
