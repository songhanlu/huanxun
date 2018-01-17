package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.StudentGradeMapper;
import com.bdqn.huanxun.pojo.StudentGrade;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2018/1/8.
 * Created by 佳 on 2018/1/15.
 */
@Service
public class StudentGradeServiceImpl implements StudentGradeService {
    @Resource
    private StudentGradeMapper studentGradeMapper;

    @Override
    public List<StudentGrade> findAllStudentGrade() {
        return studentGradeMapper.findAllStudentGrade();
    }
    @Override
    public List<StudentGrade> queryStuGrade() {
        return studentGradeMapper.queryStuGrade();
    }

}
