package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.PartTeacherTime;

import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
public interface PartTeacherTimeService {
    //根据teacherID查询兼职教师表
    List<PartTeacherTime> findPTTime(Integer teacherID);
}
