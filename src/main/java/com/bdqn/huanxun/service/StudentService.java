package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.Student;
import com.github.pagehelper.PageInfo;

/**
 * Created by 佳 on 2018/1/12.
 */
public interface StudentService {
    public PageInfo<Student> queryStudent(Integer pageNum,Integer pageSize);

    public PageInfo<Student> queryStudentByNameAndAgencyAndAge(Integer pageNum, Integer pageSize,
                                                               String stuName, String agencyName,
                                                               Integer stuAgeMin, Integer stuAgeMax);


/**
 * Created by hp on 2018/1/9.
 */
public interface StudentService {
    //根据学生年级编号选择学生列表
    List<Student> findfStudentSByGradeID(Integer stuGradeID);

}
