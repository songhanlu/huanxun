package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.CourseType;

import java.util.List;

/**
 * Created by wangheng on 2018/1/15.
 */
public interface CourseTypeService {
    //查询全部教材类型给教材添加时的下拉框使用
    List<CourseType> queryCourseTypeToBook();
}
