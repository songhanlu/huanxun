package com.bdqn.huanxun.pojo;

/**
 * Created by hp on 2018/1/17.
 */
public class StuCourseBook {
    private Integer stuCourseBookID;
    private StudentCourse studentCourse;
    private Book book;

    public Integer getStuCourseBookID() {
        return stuCourseBookID;
    }

    public void setStuCourseBookID(Integer stuCourseBookID) {
        this.stuCourseBookID = stuCourseBookID;
    }

    public StudentCourse getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(StudentCourse studentCourse) {
        this.studentCourse = studentCourse;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "StuCourseBook{" +
                "stuCourseBookID=" + stuCourseBookID +
                ", studentCourse=" + studentCourse +
                ", book=" + book +
                '}';
    }
}
