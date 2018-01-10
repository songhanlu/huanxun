package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.StudentClassArrange;
import com.github.pagehelper.PageInfo;

/**
 * Created by hp on 2018/1/9.
 */
public interface StudentClassArrangeService {
    //根据stuCourseID查询课表列表
    PageInfo<StudentClassArrange> findstuClassArranges(Integer pageNum, Integer pageSize, Integer stuCourseID);
    //添加课表
    Integer addStuClassArrange(StudentClassArrange studentClassArrange);
    //根据stuCourseID查询课表总数
    Integer countClassArrange(Integer stuCourseID);
    //删除课表
    Integer deleteClassArrange(String IDs);
    //根据stuClassArrangeID查询课表
    StudentClassArrange findStuClassArrangeByID(Integer id);
    //修改课表
    Integer updateArrange(StudentClassArrange studentClassArrange);
}
