package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.StudentGrade;

import java.util.List;

/**
 * Created by 佳 on 2018/1/15.
 */
public interface StudentGradeService {
    /**
     * 查询所有的年级
     * @return
     */
    public List<StudentGrade> queryStuGrade();
}
