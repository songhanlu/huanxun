package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by 佳 on 2018/1/12.
 */
public interface StudentMapper {
    /**
     * 查询所有学生信息
     * @return
     */
    public List<Student> queryStudent();

    /**
     * 根据年龄姓名机构模糊查询
     * @param stuName
     * @param agencyName
     * @param stuAgeMin
     * @param stuAgeMax
     * @return
     */
    public List<Student> queryStudentByNameAndAgencyAndAge(@Param("stuName") String stuName,
                                                           @Param("agencyName") String agencyName,
                                                           @Param("stuAgeMin") Integer stuAgeMin,
                                                           @Param("stuAgeMax") Integer stuAgeMax);

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

    /**
     * 根据多个id查询学生集合
     * @param list
     * @return
     */
    public List<Student> queryStudentByStuIdList(List<Integer> list);



}
