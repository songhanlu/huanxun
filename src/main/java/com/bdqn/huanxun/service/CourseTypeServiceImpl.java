package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.CourseTypeMapper;
import com.bdqn.huanxun.pojo.CourseType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
@Service
public class CourseTypeServiceImpl implements CourseTypeService {
    @Resource
    private CourseTypeMapper courseTypeMapper;

    @Override
    public List<CourseType> findAllCourseTypes() {
        return courseTypeMapper.findAllCourseTypes();
    }
}
