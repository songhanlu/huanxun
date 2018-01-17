package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by 佳 on 2018/1/12.
 */
public interface StudentService {
    /**
     * 查询学生
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Student> queryStudent(Integer pageNum,Integer pageSize);

    /**
     * 模糊查询
     * @param pageNum
     * @param pageSize
     * @param stuName
     * @param agencyName
     * @param stuAgeMin
     * @param stuAgeMax
     * @return
     */
    public PageInfo<Student> queryStudentByNameAndAgencyAndAge(Integer pageNum, Integer pageSize,
                                                               String stuName, String agencyName,
                                                               Integer stuAgeMin, Integer stuAgeMax);

    List<Student> findfStudentSByGradeID(Integer stuGradeID);

    /**
     * 添加
     * @param student
     * @return
     */
    public Integer addStudent(Student student);

    /**
     * 修改学生
     * @param student
     * @return
     */
    public Integer updateStudent(Student student);

    /**
     * 根据学生ID查询学生信息
     * @param stuId
     * @return
     */
    public Student queryStudentByStuId(Integer stuId);

    /**
     * 单条删除
     * @param stuId
     * @return
     */
    public Integer deleteStudentById(Integer stuId);

    /**
     * 批量删除
     * @param list
     * @return
     */
    public Integer deleteStudentByList(List<Integer> list);
}
