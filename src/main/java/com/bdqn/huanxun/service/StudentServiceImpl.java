package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.StudentMapper;
import com.bdqn.huanxun.pojo.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    public List<Student> findfStudentSByGradeID(Integer stuGradeID) {
        return studentMapper.findfStudentSByGradeID(stuGradeID);
    }
}
