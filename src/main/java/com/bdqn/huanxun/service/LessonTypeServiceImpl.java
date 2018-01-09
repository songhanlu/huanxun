package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.LessonTypeMapper;
import com.bdqn.huanxun.pojo.LessonType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
@Service
public class LessonTypeServiceImpl implements LessonTypeService {
    @Resource
    private LessonTypeMapper lessonTypeMapper;

    @Override
    public List<LessonType> findAllLessonTypes() {
        return lessonTypeMapper.findAllLessonTypes();
    }
}
