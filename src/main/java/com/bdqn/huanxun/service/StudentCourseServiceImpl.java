package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.StudentCourseMapper;
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
    public PageInfo<StudentCourse> findAllStudentCourse(Integer pageNum, Integer pageSize,
                                                        StudentCourse studentCourse) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentCourse> studentCourses = studentCourseMapper.findAllStudentCourse(studentCourse);
        return new PageInfo<>(studentCourses);
    }

    @Override
    public StudentCourse findStudentCourseByID(Integer id) {
        return studentCourseMapper.findStudentCourseByID(id);
    }
}
