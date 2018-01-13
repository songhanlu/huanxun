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

    public List<Student> queryStudentByNameAndAgencyAndAge(@Param("stuName") String stuName,
                                                           @Param("agencyName") String agencyName,
                                                           @Param("stuAgeMin") Integer stuAgeMin,
                                                           @Param("stuAgeMax") Integer stuAgeMax);
}
