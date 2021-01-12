package com.sheng.controller;

import com.sheng.service.AdminService;
import com.sheng.utils.JWTUtils;
import com.sheng.vo.admin.AdminUser;
import com.sheng.vo.admin.AdminVo;
import com.sheng.vo.JsonVo;
import com.sheng.vo.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class LoginController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody JsonVo login(@RequestBody AdminUser adminUser){
        AdminVo adminVo = adminService.queryByName(adminUser);
        //创建Meta对象
        Meta meta = new Meta();
        //创建JsonVo对象
        JsonVo jsonVo = new JsonVo();
        if(adminVo == null){
            meta.setMsg("用户名或密码错误");
            meta.setStatus(400);
            return jsonVo;
        }else {
            meta.setMsg("登陆成功");
            meta.setStatus(200);
        }
        //封装token令牌
        HashMap<String,String> map = new HashMap<>();
        map.put("id", adminVo.getId()+"");
        map.put("username", adminVo.getUsername());
        String token = JWTUtils.getToken(map);
        adminVo.setToken(token);
        jsonVo.setData(adminVo);
        jsonVo.setMeta(meta);
        return jsonVo;
    }




}
