package com.sheng.service.impl;

import com.sheng.dao.UserMapper;
import com.sheng.pojo.User;
import com.sheng.service.UserService;
import com.sheng.vo.user.UserLike;
import com.sheng.vo.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    //调用dao层的操作，设置一个set接口，方便Spring管理
    private UserMapper userMapper;


    @Override
    public List<Users> queryUserByQuery(UserLike userLike) {
        return userMapper.queryUserByQuery(userLike);
    }

    @Override
    public int DeleteUser(int id) {
        return userMapper.DeleteUser(id);
    }

    @Override
    public int updateType(Map<String, Object> map) {
        return userMapper.updateType(map);
    }

    @Override
    public int addUSer(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int queryTotalByQuery(String query) {
        return userMapper.queryTotalByQuery(query);
    }

    @Override
    public int updateRoleName(User user) {
        return userMapper.updateRoleName(user);
    }


}
