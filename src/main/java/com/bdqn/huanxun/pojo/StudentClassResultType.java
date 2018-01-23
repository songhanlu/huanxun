package com.bdqn.huanxun.pojo;

/**
 * Created by hp on 2018/1/21.
 */
public class StudentClassResultType {
    private Integer stuClassResultTypeID;
    private String stuClassResultTypeName;
    private String stuClassResultTypeCode;

    @Override
    public String toString() {
        return "StudentClassResultType{" +
                "stuClassResultTypeID=" + stuClassResultTypeID +
                ", stuClassResultTypeName='" + stuClassResultTypeName + '\'' +
                ", stuClassResultTypeCode='" + stuClassResultTypeCode + '\'' +
                '}';
    }

    public Integer getStuClassResultTypeID() {
        return stuClassResultTypeID;
    }

    public void setStuClassResultTypeID(Integer stuClassResultTypeID) {
        this.stuClassResultTypeID = stuClassResultTypeID;
    }

    public String getStuClassResultTypeName() {
        return stuClassResultTypeName;
    }

    public void setStuClassResultTypeName(String stuClassResultTypeName) {
        this.stuClassResultTypeName = stuClassResultTypeName;
    }

    public String getStuClassResultTypeCode() {
        return stuClassResultTypeCode;
    }

    public void setStuClassResultTypeCode(String stuClassResultTypeCode) {
        this.stuClassResultTypeCode = stuClassResultTypeCode;
    }
}
