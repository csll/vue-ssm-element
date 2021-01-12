package com.sheng.vo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminVo {
    private int id;
    private int rid;
    private String username;
    private String mobile;
    private String email;
    private String token;
}
