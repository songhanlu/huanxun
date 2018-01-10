package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.StudentCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hp on 2018/1/7.
 */
public interface StudentCourseMapper {
    //查询所有学生购买的课程（多条件）
    List<StudentCourse> findAllStudentCourse(StudentCourse studentCourse,
                                             @Param("stuName") String stuName);

    //根据stuCourseID查询学生课程
    StudentCourse findStudentCourseByID(Integer stuCourseID);

    //新增学生课程
    Integer addStudentCourse(StudentCourse studentCourse);

    //修改学生课程
    Integer updateStudentCourse(StudentCourse studentCourse);
}
