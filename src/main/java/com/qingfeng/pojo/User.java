package com.qingfeng.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 *
 * @author 清风学Java
 * @version 1.0.0
 * @date 2021/12/1
 */
public class User implements Serializable {

    /**
     * 用户主键Id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 是否是管理员  0：否  1：是
     */
    private Integer isAdmin;
    /**
     * 手机
     */
    private String phone;
    /**
     * 性别  0：保密  1：男  2：女
     */
    private Integer gender;
    /**
     * 状态（是否激活） 0：否  1：是
     */
    private Integer userStatus;
    /**
     * 创建时间
     */
    private Date userCreateTime;
    /**
     * 更新时间
     */
    private Date userUpdateTime;
    /**
     * 是否删除  0：否  1：是
     */
    private Integer isDelete;
    /**
     * 是否会员  0：否  1：是
     */
    private Integer isMember;
    /**
     * 账户余额
     */
    private Double balance;

    public User() {
    }

    public User(Long userId, String username, String password, String nickName, Integer isAdmin, String phone, Integer gender, Integer userStatus, Date userCreateTime, Date userUpdateTime, Integer isDelete, Integer isMember, Double balance) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.isAdmin = isAdmin;
        this.phone = phone;
        this.gender = gender;
        this.userStatus = userStatus;
        this.userCreateTime = userCreateTime;
        this.userUpdateTime = userUpdateTime;
        this.isDelete = isDelete;
        this.isMember = isMember;
        this.balance = balance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public Date getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(Date userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsMember() {
        return isMember;
    }

    public void setIsMember(Integer isMember) {
        this.isMember = isMember;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", isAdmin=" + isAdmin +
                ", phone='" + phone + '\'' +
                ", gender=" + gender +
                ", userStatus=" + userStatus +
                ", userCreateTime=" + userCreateTime +
                ", userUpdateTime=" + userUpdateTime +
                ", isDelete=" + isDelete +
                ", isMember=" + isMember +
                ", balance=" + balance +
                '}';
    }
}
