package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.CourseType;

import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
public interface CourseTypeService {
    //查询所有CourseType
    List<CourseType> findAllCourseTypes();
    //查询全部教材类型给教材添加时的下拉框使用
    List<CourseType> queryCourseTypeToBook();
}
