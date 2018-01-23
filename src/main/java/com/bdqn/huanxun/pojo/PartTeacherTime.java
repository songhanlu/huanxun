package com.bdqn.huanxun.pojo;

import java.sql.Time;

/**
 * Created by hp on 2018/1/9.
 */
public class PartTeacherTime {
    private Integer partTeacherTimeID;
    private Integer teacherID;
    private Integer weekDay;
    private Time ptStartTime;
    private Time ptEndTime;
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Integer getPartTeacherTimeID() {
        return partTeacherTimeID;
    }

    public void setPartTeacherTimeID(Integer partTeacherTimeID) {
        this.partTeacherTimeID = partTeacherTimeID;
    }

    public Integer getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Integer teacherID) {
        this.teacherID = teacherID;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    public Time getPtStartTime() {
        return ptStartTime;
    }

    public void setPtStartTime(Time ptStartTime) {
        this.ptStartTime = ptStartTime;
    }

    public Time getPtEndTime() {
        return ptEndTime;
    }

    public void setPtEndTime(Time ptEndTime) {
        this.ptEndTime = ptEndTime;
    }

    @Override
    public String toString() {
        return "PartTeacherTime{" +
                "partTeacherTimeID=" + partTeacherTimeID +
                ", teacherID=" + teacherID +
                ", weekDay=" + weekDay +
                ", ptStartTime=" + ptStartTime +
                ", ptEndTime=" + ptEndTime +
                '}';
    }
}
