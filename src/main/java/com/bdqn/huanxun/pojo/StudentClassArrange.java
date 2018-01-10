package com.bdqn.huanxun.pojo;

import java.util.Date;

/**
 * Created by hp on 2018/1/9.
 */
public class StudentClassArrange {
    private Integer stuClassArrangeID;
    private Integer stuCourseID;
    private Date startTime;
    private Date endTime;
    private Integer lessonNumber;
    private Teacher teacher;

    public Integer getStuClassArrangeID() {
        return stuClassArrangeID;
    }

    public void setStuClassArrangeID(Integer stuClassArrangeID) {
        this.stuClassArrangeID = stuClassArrangeID;
    }

    public Integer getStuCourseID() {
        return stuCourseID;
    }

    public void setStuCourseID(Integer stuCourseID) {
        this.stuCourseID = stuCourseID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(Integer lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "StudentClassArrange{" +
                "stuClassArrangeID=" + stuClassArrangeID +
                ", stuCourseID=" + stuCourseID +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", lessonNumber=" + lessonNumber +
                ", teacher=" + teacher +
                '}';
    }
}
