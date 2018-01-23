package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.Teacher;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
public interface TeacherService {
    List<Teacher> findTeachersByCareerType(String careerType);
    PageInfo<Teacher> queryAll(Integer pageNum, Integer pageSize);
    Teacher queryTeacherById(Integer teacherID);
    Integer deleteTeacherById(Integer teacherID);
    Integer deleteTeacherList(List<Integer> teacherIDs);
    List<Teacher> queryTeacherAll();
    Integer updateTeacher(Teacher teacher);

    Integer addTeacher(Teacher teacher,String loginName,String loginPassword);
    PageInfo<Teacher> queryAllByNameAndGender(Integer pageNum,
                                              Integer pageSize,
                                              String teacherName,
                                              String loginName);

    List<String> queryPhoto();
}
