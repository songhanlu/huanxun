package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.StudentGrade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2018/1/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class StudentGradeServiceImplTest {
    @Resource
    private StudentGradeService studentGradeService;

    @Test
    public void findAllStudentGrade() throws Exception {
        List<StudentGrade> studentGrades = studentGradeService.findAllStudentGrade();
        System.out.println(studentGrades);
    }

}