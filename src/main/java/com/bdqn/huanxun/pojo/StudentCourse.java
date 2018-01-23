package com.bdqn.huanxun.pojo;

/**
 * Created by hp on 2018/1/7.
 */
public class StudentCourse {
    private Integer stuCourseID;
    private Student student;
    private Teacher teacher;
    private LessonType lessonType;
    private CourseType courseType;
    private Integer lessonTotalNumber;
    private Integer lessonRestNumber;
    private Double discount;
    private Integer isArranged;
    private Integer teacherID;
    private Integer agencyID;
    private String stuCourseStatus;

    public String getStuCourseStatus() {
        return stuCourseStatus;
    }

    public void setStuCourseStatus(String stuCourseStatus) {
        this.stuCourseStatus = stuCourseStatus;
    }

    @Override
    public String toString() {
        return "StudentCourse{" +
                "stuCourseID=" + stuCourseID +
                ", student=" + student +
                ", teacher=" + teacher +
                ", lessonType=" + lessonType +
                ", courseType=" + courseType +
                ", lessonTotalNumber=" + lessonTotalNumber +
                ", lessonRestNumber=" + lessonRestNumber +
                ", discount=" + discount +
                ", isArranged=" + isArranged +
                ", teacherID=" + teacherID +
                ", agencyID=" + agencyID +
                '}';
    }

    public Integer getStuCourseID() {
        return stuCourseID;
    }

    public void setStuCourseID(Integer stuCourseID) {
        this.stuCourseID = stuCourseID;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public Integer getLessonTotalNumber() {
        return lessonTotalNumber;
    }

    public void setLessonTotalNumber(Integer lessonTotalNumber) {
        this.lessonTotalNumber = lessonTotalNumber;
    }

    public Integer getLessonRestNumber() {
        return lessonRestNumber;
    }

    public void setLessonRestNumber(Integer lessonRestNumber) {
        this.lessonRestNumber = lessonRestNumber;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getIsArranged() {
        return isArranged;
    }

    public void setIsArranged(Integer isArranged) {
        this.isArranged = isArranged;
    }

    public Integer getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(Integer teacherID) {
        this.teacherID = teacherID;
    }

    public Integer getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(Integer agencyID) {
        this.agencyID = agencyID;
    }
}
