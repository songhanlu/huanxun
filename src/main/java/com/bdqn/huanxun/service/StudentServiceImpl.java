package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.StudentMapper;
import com.bdqn.huanxun.pojo.Student;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**

 * Created by 佳 on 2018/1/12.

 */
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    public PageInfo<Student> queryStudent(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> list = studentMapper.queryStudent();
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Student> queryStudentByNameAndAgencyAndAge(Integer pageNum, Integer pageSize, String stuName, String agencyName, Integer stuAgeMin, Integer stuAgeMax) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> list = studentMapper.queryStudentByNameAndAgencyAndAge(stuName, agencyName, stuAgeMin, stuAgeMax);
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        return pageInfo;


    @Override
    public List<Student> findfStudentSByGradeID(Integer stuGradeID) {
        return studentMapper.findfStudentSByGradeID(stuGradeID);

    }
}
