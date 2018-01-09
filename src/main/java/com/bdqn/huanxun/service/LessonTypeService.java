package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.LessonType;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by lenovo on 2018/1/9.
 */
public interface LessonTypeService {
    //查找全部
    public PageInfo<LessonType> queryAllLessonType(Integer pageNum, Integer pageSize);
    //通过地区名称查找
    public List<LessonType> queryAllLessonTypeByLessonType(LessonType lessonArea);
    //修改 名称 时间 金额
    public Integer updateLessonDescAndLessonPriceAndTimePerLessonById(LessonType lessonType);
    //添加课程
    public Integer queryAddLessonType(LessonType lessonType);
}
