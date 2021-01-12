package com.sheng.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String mobile;
    private int type;
    private String email;
    private Date createTime;
    private boolean mgState;
    private String roleName;
}
