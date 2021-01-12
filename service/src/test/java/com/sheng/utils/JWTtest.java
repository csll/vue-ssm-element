package com.sheng.utils;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.sheng.vo.admin.AdminVo;
import org.junit.Test;

import java.util.HashMap;

public class JWTtest {

    @Test
    public void test(){
        AdminVo admin = new AdminVo(500, 0, "admin", "123", "123@qq.com", null);
        HashMap<String,String> map = new HashMap<>();
        map.put("id",admin.getId()+"");
        map.put("username", admin.getUsername());
        String token = JWTUtils.getToken(map);
        System.out.println(token);

        DecodedJWT token1 = JWTUtils.getToken(token);
        System.out.println(token1.getClaim("username").asString());
    }

    @Test
    public void test1(){
        DecodedJWT token = JWTUtils.getToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjUwMCIsImV4cCI6MTYxMDAxNDIwNiwidXNlcm5hbWUiOiJhZG1pbiJ9.6dgnJSM9WpuJks2u92vjBce4D3PAZNVYBmUJ2woLIe8");
        String id = token.getClaim("id").asString();
        System.out.println(id);
    }





}
