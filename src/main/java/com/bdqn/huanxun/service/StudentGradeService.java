package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.StudentGrade;

import java.util.List;

/**
 * Created by hp on 2018/1/8.
 */
public interface StudentGradeService {
    //查询所有学生年级
    List<StudentGrade> findAllStudentGrade();

    public List<StudentGrade> queryStuGrade();
}
