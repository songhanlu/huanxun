package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.AgencyMapper;
import com.bdqn.huanxun.dao.StudentMapper;
import com.bdqn.huanxun.pojo.Agency;
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
    @Resource
    private AgencyMapper agencyMapper;
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
    }

    @Override
    public Integer addStudent(Student student) {

        Integer agencyId = student.getAgency().getAgencyID();
        Agency agency = agencyMapper.queryAgencyById(agencyId);
        agencyMapper.updateAgencyByStuNumber(agency.getStuNumber()+1, agency.getAgencyID());
        return studentMapper.addStudent(student);
    }

    @Override
    public Integer updateStudent(Student student) {

        return studentMapper.updateStudent(student);
    }

    @Override
    public Student queryStudentByStuId(Integer stuId) {
        return studentMapper.queryStudentByStuId(stuId);
    }

    @Override
    public Integer deleteStudentById(Integer stuId) {
        Student student = studentMapper.queryStudentByStuId(stuId);
        agencyMapper.updateAgencyByStuNumber(student.getAgency().getStuNumber() - 1, student.getAgency().getAgencyID());
        return studentMapper.deleteStudentById(stuId);
    }

    @Override
    public Integer deleteStudentByList(List<Integer> list) {
        List<Student> students = studentMapper.queryStudentByStuIdList(list);
        for (Student student : students) {
            agencyMapper.updateAgencyByStuNumber(student.getAgency().getStuNumber() - 1, student.getAgency().getAgencyID());
        }
        return studentMapper.deleteStudentByList(list);
    }

}
