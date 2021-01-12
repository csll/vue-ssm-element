package com.sheng.service;



import com.sheng.pojo.User;
import com.sheng.vo.user.UserLike;
import com.sheng.vo.user.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {
    //查询用户根据参数
    List<Users> queryUserByQuery(UserLike userLike);


    //删除指定用户
    int DeleteUser(@Param("id") int id);

    //根据id修改用户状态
    int updateType(Map<String,Object> map);

    //添加用户
    int addUSer(User user);

    //修改用户信息
    int updateUser(User user);

    //模糊查询查总记录是
    int queryTotalByQuery(@Param("query") String query);

    //修改用户身份
    int updateRoleName(User user);

}
