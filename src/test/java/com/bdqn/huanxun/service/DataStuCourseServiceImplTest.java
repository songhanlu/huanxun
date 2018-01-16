package com.bdqn.huanxun.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by hp on 2018/1/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DataStuCourseServiceImplTest {
    @Resource
    private DataStuCourseService dataStuCourseService;

    @Test
    public void findNumberByTimeType() throws Exception {
        System.out.println(dataStuCourseService.findNumberByTimeType("year"));
    }

}