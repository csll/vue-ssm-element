package com.sheng.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {


    //总记录数
    private int totalPage;
    //当前页码
    private  int pageNum;

    private List<Users> users;


}
