package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.LessonTypeMapper;
import com.bdqn.huanxun.pojo.LessonType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lenovo on 2018/1/9.
 */
@Service
public class LessonTypeServiceImpl implements LessonTypeService {
    @Resource
    private LessonTypeMapper lessonTypeMapper;

    public PageInfo<LessonType> queryAllLessonType(Integer pageNum,Integer pageSize) {
            PageHelper.startPage(pageNum,pageSize);
            List<LessonType> list=lessonTypeMapper.queryAllLessonType();
            PageInfo<LessonType> pageInfo = new PageInfo<LessonType>(list);
            return pageInfo;
    }

    public List<LessonType> queryAllLessonTypeByLessonType(LessonType lessonArea) {
        return lessonTypeMapper.queryAllLessonTypeByLessonType(lessonArea);
    }

    public Integer updateLessonDescAndLessonPriceAndTimePerLessonById(LessonType lessonType) {
        return lessonTypeMapper.updateLessonDescAndLessonPriceAndTimePerLessonById(lessonType);
    }

    public Integer queryAddLessonType(LessonType lessonType) {
        return lessonTypeMapper.queryAddLessonType(lessonType);
    }
}
