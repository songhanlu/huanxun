package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.LessonType;

import java.util.List;

/**
 * Created by lenovo on 2018/1/8.
 */
public interface LessonTypeMapper {
    //查找全部
    public List<LessonType> queryAllLessonType();
    //通过地区名称查找
    public List<LessonType> queryAllLessonTypeByLessonType(LessonType lessonArea);
    //修改 名称 时间 金额
    public Integer updateLessonDescAndLessonPriceAndTimePerLessonById(LessonType lessonType);
    //添加课程
    public Integer queryAddLessonType(LessonType lessonType);
}
