package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by 佳 on 2018/1/12.
 */
public interface StudentService {
    public PageInfo<Student> queryStudent(Integer pageNum,Integer pageSize);

    public PageInfo<Student> queryStudentByNameAndAgencyAndAge(Integer pageNum, Integer pageSize,
                                                               String stuName, String agencyName,
                                                               Integer stuAgeMin, Integer stuAgeMax);

    List<Student> findfStudentSByGradeID(Integer stuGradeID);

}
