package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.LessonType;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lenovo on 2018/1/9.
 */
public interface LessonTypeService {
    //查找全部
    public PageInfo<LessonType> queryAllLessonType(Integer pageNum, Integer pageSize,
                                                   String lessonArea,
                                                   Integer lessonPrice1,
                                                   Integer lessonPrice2,
                                                   Integer timePerLesson,
                                                   Integer lessonDesc);
        //通过条件查找全部
    public List<LessonType> queryAllLessonTypeByLessonType(@Param("lessonArea") String lessonArea ,
                                                           @Param("lessonPrice1") Integer lessonPrice1,
                                                           @Param("lessonPrice2") Integer lessonPrice2,
                                                           @Param("timePerLesson") Integer timePerLesson,
                                                           @Param("lessonDesc") Integer lessonDesc);
    //修改 名称 时间 金额
    public Integer updateLessonDescAndLessonPriceAndTimePerLessonById(LessonType lessonType);
    //添加课程
    public Integer queryAddLessonType(LessonType lessonType);
}
