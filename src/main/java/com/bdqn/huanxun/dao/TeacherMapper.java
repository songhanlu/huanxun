package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
public interface TeacherMapper {
    List<Teacher> findTeachersByCareerType(String careerType);

    List<Teacher> queryAllTeacher();

    Teacher queryTeacherById(Integer teacherID);

    Integer deleteTeacherById(Integer teacherID);

    List<Teacher> queryTeacherAll();

    Integer deleteTeacherList(List<Integer> teacherIDs);

    Integer updateTeacher(Teacher teacher);

    Integer addTeacher(Teacher teacher);

    List<Teacher> queryTeacherByUserNameAndGender(@Param("teacherName") String teacherName,
                                                  @Param("loginName") String loginName);

    List<String> queryPhoto();

}
