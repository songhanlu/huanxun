package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.CourseTypeMapper;
import com.bdqn.huanxun.pojo.CourseType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangheng on 2018/1/15.
 */
@Service
public class CourseTypeServiceImpl implements CourseTypeService {
    @Resource
    private CourseTypeMapper courseTypeMapper;
    @Override
    public List<CourseType> queryCourseTypeToBook() {
        return courseTypeMapper.queryCourseTypeToBook();
    }
}
