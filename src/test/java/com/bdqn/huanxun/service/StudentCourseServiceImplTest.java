package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.StudentCourse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by hp on 2018/1/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class StudentCourseServiceImplTest {
    @Resource
    private StudentCourseService studentCourseService;

    @Test
    public void findStudentCourseByID() throws Exception {
        int id = 1;
        StudentCourse studentCourse = studentCourseService.findStudentCourseByID(id);
        System.out.println(studentCourse);
    }

}