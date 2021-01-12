package com.sheng.controller;

import com.sheng.pojo.Role;
import com.sheng.service.RoleService;
import com.sheng.vo.JsonVo;
import com.sheng.vo.Meta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.RowId;
import java.util.List;

@RestController
@RequestMapping("roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    //查询角色列表
    @RequestMapping(value = "/roleList",produces = "application/json;charset=utf-8",
            method = RequestMethod.GET)
    public @ResponseBody JsonVo getRoleList(){
        List<Role> roleList = roleService.getRoleList();
        Meta meta = new Meta();
        JsonVo jsonVo = new JsonVo();
        if(roleList.size()==0){
            meta.setMsg("查询失败");
            meta.setStatus(400);
        }else{
//            RoleVo roleVo = new RoleVo(roleList);
            jsonVo.setData(roleList);
            meta.setMsg("查询角色成功");
            meta.setStatus(200);

        }

        jsonVo.setMeta(meta);
        return jsonVo;
    }

    //添加角色
    @RequestMapping(value = "addRole",produces = "application/json;charset=utf-8",
            method = RequestMethod.POST)
    public @ResponseBody JsonVo addRole(@RequestBody Role role){

        Meta meta = new Meta();
        JsonVo jsonVo = new JsonVo();

        //检查角色是否存在
        Role role1 = roleService.queryRoleByName(role.getRoleName());
        if(role1==null){
            int i = roleService.addRole(role);
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
        else{
            meta.setMsg("添加失败");
            meta.setStatus(404);
            jsonVo.setMeta(meta);
            return jsonVo;
        }


    }

    //删除角色
    @RequestMapping(value = "/deleteRole",produces = "application/json;charset=utf-8",
            method = RequestMethod.GET)
    public @ResponseBody JsonVo deleteUser(String roleName){
        Meta meta = new Meta();
        JsonVo jsonVo = new JsonVo();
        int i = roleService.deleteRoleByName(roleName);
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

}
