package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.StudentCourse;

import java.util.List;

/**
 * Created by hp on 2018/1/7.
 */
public interface StudentCourseMapper {
    //查询所有学生购买的课程（多条件）
    List<StudentCourse> findAllStudentCourse(StudentCourse studentCourse);

    //根据stuCourseID查询学生课程
    StudentCourse findStudentCourseByID(Integer stuCourseID);

    //新增学生课程
    Integer addStudentCourse(StudentCourse studentCourse);
}
