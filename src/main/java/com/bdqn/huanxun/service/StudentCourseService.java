package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.StudentCourse;
import com.github.pagehelper.PageInfo;

/**
 * Created by hp on 2018/1/7.
 */
public interface StudentCourseService {
    //查询所有学生购买的课程（多条件）
    PageInfo<StudentCourse> findAllStudentCourse(Integer pageNum, Integer pageSize, StudentCourse studentCourse);

    //根据stuCourseID查询学生课程
    StudentCourse findStudentCourseByID(Integer id);
}
