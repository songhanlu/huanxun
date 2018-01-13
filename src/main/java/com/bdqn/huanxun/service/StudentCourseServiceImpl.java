package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.StudentCourseMapper;


import com.bdqn.huanxun.pojo.CourseType;
import com.bdqn.huanxun.pojo.StudentCourse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2018/1/7.
 */
@Service
public class StudentCourseServiceImpl implements StudentCourseService {
    @Resource
    private StudentCourseMapper studentCourseMapper;

    @Override
    public PageInfo<StudentCourse> findAllStudentCourse_liyujia(Integer pageNum, Integer pageSize,
                                                        StudentCourse studentCourse) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentCourse> studentCourses = studentCourseMapper.findAllStudentCourse_liyujia(studentCourse);
        return new PageInfo<>(studentCourses);
    }

  public PageInfo<StudentCourse> findAllStudentCourse(Integer pageNum, Integer pageSize,
                                                        StudentCourse studentCourse,
                                                        String stuName,
                                                        Integer isArranged,
                                                        Integer courseTypeID,
                                                        Integer lessonTypeID,
                                                        String sort,
                                                        String order) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentCourse> studentCourses = studentCourseMapper.findAllStudentCourse(studentCourse,stuName,isArranged,courseTypeID,lessonTypeID,sort,order);
        return new PageInfo<>(studentCourses);
    }

    @Override
    public StudentCourse findStudentCourseByID(Integer id) {
        return studentCourseMapper.findStudentCourseByID(id);
    }



    @Override
    public Integer addStudentCourse(StudentCourse studentCourse) {
        return studentCourseMapper.addStudentCourse(studentCourse);
    }

    @Override
    public Integer updateStudentCourse(StudentCourse studentCourse) {
        return studentCourseMapper.updateStudentCourse(studentCourse);
    }

    @Override
    public Integer countStuCourseByLessonTypeID(Integer lessonTypeID) {
        return studentCourseMapper.countStuCourseByLessonTypeID(lessonTypeID);
    }

    @Override
    public Integer countStuCourseByCourseTypeID(Integer courseTypeID) {
        return studentCourseMapper.countStuCourseByCourseTypeID(courseTypeID);
    }

    @Override
    public CourseType findLessonTypeByStuCourseID(Integer stuCourseID) {
        return studentCourseMapper.findCourseTypeByStuCourseID(stuCourseID);
    }

}
