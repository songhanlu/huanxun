package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.Teacher;

import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
public interface TeacherMapper {
    List<Teacher> findTeachersByCareerType(String careerType);
}
