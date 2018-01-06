package com.bdqn.huanxun.pojo;

/**
 * Created by hp on 2018/1/5.
 */
public class Employee {
    private Integer employeeID;
    private String employeeName;
    private Integer loginUserID;
    private String phone;
    private String email;
    private Integer employeeAge;
    private String employeeGender;
    private String employeeQQ;
    private Integer visible;

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", employeeName='" + employeeName + '\'' +
                ", loginUserID=" + loginUserID +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", employeeAge=" + employeeAge +
                ", employeeGender='" + employeeGender + '\'' +
                ", employeeQQ='" + employeeQQ + '\'' +
                ", visible=" + visible +
                '}';
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getLoginUserID() {
        return loginUserID;
    }

    public void setLoginUserID(Integer loginUserID) {
        this.loginUserID = loginUserID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(Integer employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getEmployeeQQ() {
        return employeeQQ;
    }

    public void setEmployeeQQ(String employeeQQ) {
        this.employeeQQ = employeeQQ;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }
}
