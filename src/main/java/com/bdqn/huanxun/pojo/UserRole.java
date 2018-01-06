package com.bdqn.huanxun.pojo;

/**
 * Created by hp on 2018/1/4.
 */
public class UserRole {
    private Integer userRoleID;
    private String userRoleName;

    @Override
    public String toString() {
        return "UserRole{" +
                "userRoleID=" + userRoleID +
                ", userRoleName='" + userRoleName + '\'' +
                '}';
    }

    public Integer getUserRoleID() {
        return userRoleID;
    }

    public void setUserRoleID(Integer userRoleID) {
        this.userRoleID = userRoleID;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }
}
