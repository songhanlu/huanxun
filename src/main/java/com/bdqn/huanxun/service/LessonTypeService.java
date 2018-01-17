package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.LessonType;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by lenovo on 2018/1/9.
 */
public interface LessonTypeService {
    //ID查找
    public LessonType queryLessonTypeById(Integer lessonTypeID);
    //通过条件查找全部
    public PageInfo<LessonType> queryAllLessonTypeByLessonType(Integer pageNum, Integer pageSize,
                                                                String lessonArea ,
                                                                Integer lessonPrice1,
                                                                Integer lessonPrice2,
                                                                Integer timePerLesson,
                                                                String lessonDesc);
    //修改 名称 时间 金额
    public Integer updateLessonDescAndLessonPriceAndTimePerLessonById(LessonType lessonType);
    //添加课程
    public Integer queryAddLessonType(LessonType lessonType);
    //数据统计
    List<LessonType> findAllLessonTypes_Data();
    //ID删除
    public Integer deleteLessonTypeById(Integer lessonTypeID);
    //批量删除
    public Integer deleteLessonTypeByIdList(List<Integer> IdList);
    List<LessonType> querylessonDescBylessonArea(String lessonArea);


}
