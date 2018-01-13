package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.Student;

import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
public interface StudentMapper {
    //根据学生年级编号选择学生列表
    List<Student> findfStudentSByGradeID(Integer stuGradeID);
}
