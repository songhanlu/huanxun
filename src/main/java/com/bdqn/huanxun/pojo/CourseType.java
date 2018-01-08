package com.bdqn.huanxun.pojo;

/**
 * Created by hp on 2018/1/7.
 */
public class CourseType {
    private Integer courseTypeID;
    private String courseTypeName;
    private Integer visible;

    public Integer getCourseTypeID() {
        return courseTypeID;
    }

    public void setCourseTypeID(Integer courseTypeID) {
        this.courseTypeID = courseTypeID;
    }

    public String getCourseTypeName() {
        return courseTypeName;
    }

    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "CourseType{" +
                "courseTypeID=" + courseTypeID +
                ", courseTypeName='" + courseTypeName + '\'' +
                ", visible=" + visible +
                '}';
    }
}
