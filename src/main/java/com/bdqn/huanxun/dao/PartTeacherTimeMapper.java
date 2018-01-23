package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.PartTeacherTime;

import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
public interface PartTeacherTimeMapper {
    //根据teacherID查询兼职教师表
    List<PartTeacherTime> findPTTime(Integer teacherID);

    List<PartTeacherTime> queryPartTeacherTimeByTeacherID(Integer teacherID);
    Integer deleteTeacherTime(Integer partTeacherTimeID);
    Integer deleteTeacherTimeList(List<Integer> partTeacherTimeIDs);

    Integer addTeacherTime(PartTeacherTime partTeacherTime);
}
