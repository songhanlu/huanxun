package com.bdqn.huanxun.service;


import com.bdqn.huanxun.pojo.CourseType;
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

    //根据课程LessonTypeID查询课程数量
    Integer countStuCourseByLessonTypeID(Integer lessonTypeID);

    //根据课程CourseTypeID查询课程数量
    Integer countStuCourseByCourseTypeID(Integer courseTypeID);

    //根据stuCourseID查询学生课程查询LessonType集合
    CourseType findLessonTypeByStuCourseID(Integer stuCourseID);

    //改变课程状态（进行中-->已完成）
    Integer updateStuCourseStatusToFinished(String IDs);

    //改变课程状态（进行中-->进行中）
    Integer updateStuCourseStatusToActive(String IDs);
}
