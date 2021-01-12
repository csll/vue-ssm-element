package com.sheng.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sheng.pojo.User;
import com.sheng.service.UserService;
import com.sheng.vo.JsonVo;
import com.sheng.vo.Meta;
import com.sheng.vo.user.UserLike;
import com.sheng.vo.user.UserVo;
import com.sheng.vo.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("users")
public class UserController {
    @Autowired
    public UserService userService;

    //查询用户列表
    @RequestMapping(value = "/userList",produces = "application/json;charset=utf-8",
            method = RequestMethod.GET)
    public @ResponseBody JsonVo userList(UserLike userLike){
        userLike.setStartIndex(userLike.getPageSize(), userLike.getPageNum());
        System.out.println(userLike);
        Meta meta = new Meta();
        JsonVo jsonVo = new JsonVo();

        System.out.println(userLike.getQuery()==null);

        List<Users> users = userService.queryUserByQuery(userLike);
        int num = userService.queryTotalByQuery(userLike.getQuery());
        if(users.size()==0){
            meta.setStatus(400);
            meta.setMsg("查询失败");
            jsonVo.setMeta(meta);
            return jsonVo;

        }
        //userVo  ==  data
        UserVo userVo = new UserVo();

        userVo.setUsers(users);
        userVo.setPageNum(userLike.getPageNum());
        userVo.setTotalPage(num);
        meta.setMsg("获取成功");
        meta.setStatus(200);
        jsonVo.setData(userVo);
        jsonVo.setMeta(meta);

        return jsonVo;

    }

    //根据id删除指定用户
    @RequestMapping(value = "/delete",produces = "application/json;charset=utf-8",
            method = RequestMethod.GET)
    public @ResponseBody JsonVo deleteUser(int id){
        Meta meta = new Meta();
        JsonVo jsonVo = new JsonVo();
        int i = userService.DeleteUser(id);
        if(i == 0){
            meta.setMsg("删除失败");
            meta.setStatus(402);

        }else {
            meta.setMsg("删除成功");
            meta.setStatus(200);
        }
        jsonVo.setMeta(meta);
        return jsonVo;


    }

    //根据id修改用户状态
    @RequestMapping(value = "/type",produces = "application/json;charset=utf-8",
            method = RequestMethod.GET)
    public @ResponseBody JsonVo updateType(int id,boolean type){
        Meta meta = new Meta();
        JsonVo jsonVo = new JsonVo();
        HashMap<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("type", type);
        System.out.println(map);
        int i = userService.updateType(map);
        if(i == 0){
            meta.setMsg("修改失败");
            meta.setStatus(402);

        }else {
            meta.setMsg("修改成功");
            meta.setStatus(200);
        }
        jsonVo.setMeta(meta);
        return jsonVo;

    }

    //添加用户
    @RequestMapping(value="/addUser",produces = "application/json;charset=utf-8",
                    method = RequestMethod.POST)
    public @ResponseBody JsonVo addUser(@RequestBody User user){
        Meta meta = new Meta();
        JsonVo jsonVo = new JsonVo();
        int i = userService.addUSer(user);
        if(i == 1){
            meta.setMsg("添加成功");
            meta.setStatus(200);

        }else {
            meta.setMsg("添加失败");
            meta.setStatus(400);
        }
        jsonVo.setMeta(meta);
        return jsonVo;
    }

    //修改用户信息
    @RequestMapping(value="/updateUser",produces = "application/json;charset=utf-8",
            method = RequestMethod.POST)
    public @ResponseBody JsonVo updateUser(@RequestBody User user){
        Meta meta = new Meta();
        JsonVo jsonVo = new JsonVo();
        int i = userService.updateUser(user);
        if(i == 1){
            meta.setMsg("用户信息修改成功");
            meta.setStatus(200);

        }else {
            meta.setMsg("用户信息修改失败");
            meta.setStatus(400);
        }
        jsonVo.setMeta(meta);
        return jsonVo;
    }

    //修改用户角色
    @RequestMapping(value="/updateRole",produces = "application/json;charset=utf-8",
            method = RequestMethod.POST)
    public @ResponseBody JsonVo updateRole(@RequestBody User user){
        Meta meta = new Meta();
        JsonVo jsonVo = new JsonVo();
        String roleName = user.getRoleName();
        int i = userService.updateRoleName(user);
        if(i == 1){
            meta.setMsg("用户身份修改成功");
            meta.setStatus(200);

        }else {
            meta.setMsg("用户身份修改失败");
            meta.setStatus(400);
        }
        jsonVo.setMeta(meta);
        return jsonVo;
    }
}
