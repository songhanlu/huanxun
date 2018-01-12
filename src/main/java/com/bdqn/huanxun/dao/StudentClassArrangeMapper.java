package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.StudentClassArrange;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
public interface StudentClassArrangeMapper {
    //根据stuCourseID查询课表列表
    List<StudentClassArrange> findstuClassArranges(@Param("stuCourseID") Integer stuCourseID);
    //添加课表
    Integer addStuClassArrange(StudentClassArrange studentClassArrange);
    //根据stuCourseID查询课表总数
    Integer countClassArrange(Integer stuCourseID);
    //删除课表
    Integer deleteClassArrange(List<Integer> ids);
    //根据stuClassArrangeID查询课表
    StudentClassArrange findStuClassArrangeByID(Integer id);
    //修改课表
    Integer updateArrange(StudentClassArrange studentClassArrange);

    //根据teacherID查询该外教课程表
    List<StudentClassArrange> findStuClassArrangeByTeaherID(Integer teacherID);
}
