package com.bdqn.huanxun.pojo.data;

import com.bdqn.huanxun.pojo.StudentCourse;

import java.util.Date;

/**
 * Created by hp on 2018/1/11.
 */
public class DataStuCourse {
    private Integer dataStuCourseID;//主键ID
    private StudentCourse studentCourse;//学生课程
    private String date_stuCourse_operate;//课程操作
    private Date data_stuCourse_operateTime;//

    @Override
    public String toString() {
        return "DataStuCourse{" +
                "dataStuCourseID=" + dataStuCourseID +
                ", studentCourse=" + studentCourse +
                ", date_stuCourse_operate='" + date_stuCourse_operate + '\'' +
                ", data_stuCourse_operateTime=" + data_stuCourse_operateTime +
                '}';
    }

    public Integer getDataStuCourseID() {
        return dataStuCourseID;
    }

    public void setDataStuCourseID(Integer dataStuCourseID) {
        this.dataStuCourseID = dataStuCourseID;
    }

    public StudentCourse getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(StudentCourse studentCourse) {
        this.studentCourse = studentCourse;
    }

    public String getDate_stuCourse_operate() {
        return date_stuCourse_operate;
    }

    public void setDate_stuCourse_operate(String date_stuCourse_operate) {
        this.date_stuCourse_operate = date_stuCourse_operate;
    }

    public Date getData_stuCourse_operateTime() {
        return data_stuCourse_operateTime;
    }

    public void setData_stuCourse_operateTime(Date data_stuCourse_operateTime) {
        this.data_stuCourse_operateTime = data_stuCourse_operateTime;
    }
}
