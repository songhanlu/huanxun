package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.StudentCourse;
import com.github.pagehelper.PageInfo;

/**
 * Created by hp on 2018/1/7.
 */
public interface StudentCourseService {
    //查询所有学生购买的课程（多条件）
    PageInfo<StudentCourse> findAllStudentCourse(Integer pageNum, Integer pageSize,
                                                 StudentCourse studentCourse,
                                                 String stuName,
                                                 Integer isArranged,
                                                 Integer courseTypeID,
                                                 Integer lessonTypeID,
                                                 String sort,
                                                 String order);

    //根据stuCourseID查询学生课程
    StudentCourse findStudentCourseByID(Integer id);

    //新增学生课程
    Integer addStudentCourse(StudentCourse studentCourse);
    //修改学生课程
    Integer updateStudentCourse(StudentCourse studentCourse);
}
