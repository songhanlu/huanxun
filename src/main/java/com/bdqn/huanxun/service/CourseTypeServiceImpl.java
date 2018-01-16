package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.CourseTypeMapper;
import com.bdqn.huanxun.pojo.CourseType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
<<<<<<< HEAD
 * Created by hp on 2018/1/9.
=======
 * Created by wangheng on 2018/1/15.
>>>>>>> V1.0/wangheng004
 */
@Service
public class CourseTypeServiceImpl implements CourseTypeService {
    @Resource
    private CourseTypeMapper courseTypeMapper;

    @Override
    public List<CourseType> findAllCourseTypes() {
        return courseTypeMapper.findAllCourseTypes();
    @Override
    public List<CourseType> queryCourseTypeToBook() {
        return courseTypeMapper.queryCourseTypeToBook();
    }
}
