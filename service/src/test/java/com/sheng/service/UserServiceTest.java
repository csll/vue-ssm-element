package com.sheng.service;

import com.sheng.service.impl.UserServiceImpl;
import com.sheng.vo.user.UserLike;
import com.sheng.vo.user.Users;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.HashMap;
import java.util.List;

@SpringJUnitConfig(locations = "classpath:applicationContext.xml")
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userServiceImpl;


    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserServiceImpl userService = context.getBean("userServiceImpl", UserServiceImpl.class);
//        System.out.println(userService.queryAllUser());
    }

    @Test
    public void test1(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserServiceImpl userService = context.getBean("userServiceImpl", UserServiceImpl.class);
        HashMap<String,Object> map = new HashMap<>();
        map.put("id", 78);
        map.put("type", true);
        int i = userService.updateType(map);
        System.out.println(i);
    }

    @Test
    public void test2(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserServiceImpl userService = context.getBean("userServiceImpl", UserServiceImpl.class);
        UserLike userLike = new UserLike("",1,4);
        List<Users> users = userService.queryUserByQuery(userLike);
        System.out.println(users);
    }

    @Test
    public void test3(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserServiceImpl userService = context.getBean("userServiceImpl", UserServiceImpl.class);
        int num = userService.queryTotalByQuery("测试");
        System.out.println(num);
    }
}