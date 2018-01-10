package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.StudentClassArrangeMapper;
import com.bdqn.huanxun.pojo.StudentClassArrange;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
@Service
public class StudentClassArrangeServiceImpl implements StudentClassArrangeService {
    @Resource
    private StudentClassArrangeMapper studentClassArrangeMapper;

    @Override
    public PageInfo<StudentClassArrange> findstuClassArranges(Integer pageNum, Integer pageSize, Integer stuCourseID) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentClassArrange> studentClassArranges = studentClassArrangeMapper.findstuClassArranges(stuCourseID);
        return new PageInfo<>(studentClassArranges);
    }

    @Override
    public Integer addStuClassArrange(StudentClassArrange studentClassArrange) {
        return studentClassArrangeMapper.addStuClassArrange(studentClassArrange);
    }

    @Override
    public Integer countClassArrange(Integer stuCourseID) {
        return studentClassArrangeMapper.countClassArrange(stuCourseID);
    }

    @Override
    public Integer deleteClassArrange(String IDs) {
        List<Integer> dd = new ArrayList<>();
        String[] ids = IDs.split(",");
        for (String id : ids) {
            dd.add(Integer.parseInt(id));
        }
        int result = studentClassArrangeMapper.deleteClassArrange(dd);
        return result;
    }

    @Override
    public StudentClassArrange findStuClassArrangeByID(Integer id) {
        return studentClassArrangeMapper.findStuClassArrangeByID(id);
    }

    @Override
    public Integer updateArrange(StudentClassArrange studentClassArrange) {
        return studentClassArrangeMapper.updateArrange(studentClassArrange);
    }
}
