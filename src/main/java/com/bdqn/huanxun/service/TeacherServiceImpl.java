package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.TeacherMapper;
import com.bdqn.huanxun.pojo.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> findTeachersByCareerType(String careerType) {
        return teacherMapper.findTeachersByCareerType(careerType);
    }
}
