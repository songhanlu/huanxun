package com.bdqn.huanxun.pojo;

/**
 * Created by hp on 2018/1/6.
 */
public class StudentGrade {
    private Integer stuGradeID;
    private String stuGradeName;

    @Override
    public String toString() {
        return "StudentGrade{" +
                "stuGradeID=" + stuGradeID +
                ", stuGradeName='" + stuGradeName + '\'' +
                '}';
    }

    public Integer getStuGradeID() {
        return stuGradeID;
    }

    public void setStuGradeID(Integer stuGradeID) {
        this.stuGradeID = stuGradeID;
    }

    public String getStuGradeName() {
        return stuGradeName;
    }

    public void setStuGradeName(String stuGradeName) {
        this.stuGradeName = stuGradeName;
    }
}
