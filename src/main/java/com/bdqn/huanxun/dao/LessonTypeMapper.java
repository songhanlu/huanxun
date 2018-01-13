package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.LessonType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lenovo on 2018/1/8.
 */
public interface LessonTypeMapper {
    //查找全部
    public List<LessonType> queryAllLessonType();
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
