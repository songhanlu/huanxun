package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.CourseType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wangheng on 2018/1/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CourseTypeServiceImplTest {
    @Resource
    private CourseTypeService courseTypeService;
    @Test
    public void queryCourseTypeToBook() throws Exception {
        List<CourseType> list=courseTypeService.queryCourseTypeToBook();
        for (CourseType courseType : list) {
            System.out.println(courseType);
        }
    }

}