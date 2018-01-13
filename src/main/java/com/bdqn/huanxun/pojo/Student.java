package com.bdqn.huanxun.pojo;

/**
 * Created by hp on 2018/1/6.
 */
public class Student {
    private Integer stuID;
    private String stuName;
    private Integer stuAge;
    private String stuPhotoAddress;
    private String stuGender;
    private String stuPhone;
    private String stuEmail;
    private String stuQQ;
    private StudentGrade studentGrade;
    private Agency agency;
    private Integer visible;

    public Integer getStuID() {
        return stuID;
    }

    public void setStuID(Integer stuID) {
        this.stuID = stuID;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuPhotoAddress() {
        return stuPhotoAddress;
    }

    public void setStuPhotoAddress(String stuPhotoAddress) {
        this.stuPhotoAddress = stuPhotoAddress;
    }

    public String getStuGender() {
        return stuGender;
    }

    public void setStuGender(String stuGender) {
        this.stuGender = stuGender;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public String getStuQQ() {
        return stuQQ;
    }

    public void setStuQQ(String stuQQ) {
        this.stuQQ = stuQQ;
    }

    public StudentGrade getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(StudentGrade studentGrade) {
        this.studentGrade = studentGrade;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuID=" + stuID +
                ", stuName='" + stuName + '\'' +
                ", stuAge=" + stuAge +
                ", stuPhotoAddress='" + stuPhotoAddress + '\'' +
                ", stuGender='" + stuGender + '\'' +
                ", stuPhone='" + stuPhone + '\'' +
                ", stuEmail='" + stuEmail + '\'' +
                ", stuQQ='" + stuQQ + '\'' +
                ", studentGrade=" + studentGrade +
                ", agency=" + agency +
                ", visible=" + visible +
                '}';
    }
}
