package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.LessonType;

import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
public interface LessonTypeService {
    //查询所有LessonType
    List<LessonType> findAllLessonTypes();
}
