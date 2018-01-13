package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.Student;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by 佳 on 2018/1/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class StudentServiceTest {
    @Resource
    private StudentService studentService;

    @Test
    public void queryStudentByNameAndAgencyAndAge() throws Exception {
        PageInfo<Student> pageInfo = studentService.queryStudentByNameAndAgencyAndAge(1, 10, null, null, null,null);

    }

}