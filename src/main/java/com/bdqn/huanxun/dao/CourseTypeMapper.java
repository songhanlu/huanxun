package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.CourseType;

import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
public interface CourseTypeMapper {
    //查询所有CourseType
    List<CourseType> findAllCourseTypes();
}
