package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.Teacher;

import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
public interface TeacherService {
    List<Teacher> findTeachersByCareerType(String careerType);
}
