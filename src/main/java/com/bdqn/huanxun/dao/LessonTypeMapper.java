package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.LessonType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lenovo on 2018/1/8.
 */
public interface LessonTypeMapper {
    //ID查找
    public LessonType queryLessonTypeById(Integer lessonTypeID);
    //通过条件查找全部
    public List<LessonType> queryAllLessonTypeByLessonType(@Param("lessonArea") String lessonArea ,
                                                           @Param("lessonPrice1") Integer lessonPrice1,
                                                           @Param("lessonPrice2") Integer lessonPrice2,
                                                           @Param("timePerLesson") Integer timePerLesson,
                                                           @Param("lessonDesc") String lessonDesc);
    //修改 名称 时间 金额
    public Integer updateLessonDescAndLessonPriceAndTimePerLessonById(LessonType lessonType);
    //添加课程
    public Integer queryAddLessonType(LessonType lessonType);

    //ID删除
    public Integer deleteLessonTypeById(Integer lessonTypeID);
    //批量删除
    public Integer deleteLessonTypeByIdList(List<Integer> IdList);


    //查询所有课程
    List<LessonType> findAllLessonTypes_Data();

    public List<LessonType> querylessonDescBylessonArea(String lessonArea);

}