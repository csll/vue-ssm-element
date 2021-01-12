package com.sheng.service;

import com.sheng.service.impl.AdminServiceImpl;
import com.sheng.vo.admin.AdminUser;
import com.sheng.vo.admin.AdminVo;
import junit.framework.TestCase;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AdminServiceTest extends TestCase {

    public void testQueryByName() {
        AdminUser adminUser = new AdminUser("admin","123456");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AdminServiceImpl adminService = context.getBean("adminServiceImpl", AdminServiceImpl.class);
        AdminVo adminVo = adminService.queryByName(adminUser);
        System.out.println(adminVo);

    }
}