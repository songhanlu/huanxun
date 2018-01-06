package com.bdqn.huanxun.pojo;

/**
 * Created by hp on 2018/1/4.
 */
public class LoginUser {
    private Integer loginUserID;
    private String loginName;
    private String loginPassword;
    private Integer visible;
    private UserRole userRole;

    @Override
    public String toString() {
        return "LoginUser{" +
                "loginUserID=" + loginUserID +
                ", loginName='" + loginName + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                ", userRole=" + userRole +
                '}';
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Integer getLoginUserID() {
        return loginUserID;
    }

    public void setLoginUserID(Integer loginUserID) {
        this.loginUserID = loginUserID;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}
