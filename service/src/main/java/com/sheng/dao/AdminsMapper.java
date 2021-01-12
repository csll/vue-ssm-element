package com.sheng.dao;

import com.sheng.vo.admin.AdminUser;
import com.sheng.vo.admin.AdminVo;

public interface AdminsMapper {
    //更具管理员名称查询管理员
    AdminVo queryByName(AdminUser adminUser);

}
