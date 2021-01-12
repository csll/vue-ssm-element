package com.sheng.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Users {

    private int id;
    private String username;
    private String mobile;
    private int type;
    private String email;
    //发送给前端的数据
    private String createTime;
    //从数据库获取的时间戳
    private Date createTimeCuo;
    private boolean mgState;
    private String roleName;

    public Users() {
    }

    public Users(int id, String username, String mobile, int type, String email, Date createTimeCuo, boolean mgState, String roleName) {
        this.id = id;
        this.username = username;
        this.mobile = mobile;
        this.type = type;
        this.email = email;
        this.createTimeCuo = createTimeCuo;
        this.mgState = mgState;
        this.roleName = roleName;
        setCreateTime(createTimeCuo);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", mobile='" + mobile + '\'' +
                ", type=" + type +
                ", email='" + email + '\'' +
                ", createTime='" + createTime + '\'' +
                ", mgState=" + mgState +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTimeCuo) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.createTime = sf.format(createTimeCuo);
    }


    public void setCreateTimeCuo(Date createTimeCuo) {
        this.createTimeCuo = createTimeCuo;
        setCreateTime(createTimeCuo);
    }

    public boolean isMgState() {
        return mgState;
    }

    public void setMgState(boolean mgState) {
        this.mgState = mgState;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
