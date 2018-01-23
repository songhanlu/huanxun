package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.Book;
import com.bdqn.huanxun.pojo.StudentClassArrange;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

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

    /**
     * 检测外教时间是否冲突
     * @param times 循环次数
     * @param teacherID  外教id
     * @return
     */
    Boolean teacherTimeIsNotDupe(Integer times, Integer teacherID, Date startTimeInput, Date endTimeInput, Integer currentArrangeID);

    //根据stuClassArrangeID查询该课程下可选的教材列表(为课表上传教材时使用！！)
    List<Book> findBooksCanChooseByArrangeID(Integer arrangeID);

    //修改课程结果状态
    Integer updateStuClassArrangeStatus(StudentClassArrange arrange);
}
