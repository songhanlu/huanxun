package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.PartTeacherTimeMapper;
import com.bdqn.huanxun.pojo.PartTeacherTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
@Service
public class PartTeacherTimeServiceImpl implements PartTeacherTimeService {
    @Resource
    private PartTeacherTimeMapper partTeacherTimeMapper;

    @Override
    public List<PartTeacherTime> findPTTime(Integer teacherID) {
        return partTeacherTimeMapper.findPTTime(teacherID);
    }

    @Override
    public Integer deleteTeacherTime(Integer partTeacherTimeID) {
        return partTeacherTimeMapper.deleteTeacherTime(partTeacherTimeID);
    }

    @Override
    public Integer deleteTeacherTimeList(List<Integer> partTeacherTimeIDs) {
        return partTeacherTimeMapper.deleteTeacherTimeList(partTeacherTimeIDs);
    }

    @Override
    public Integer addTeacherTime(PartTeacherTime partTeacherTime) {
        return partTeacherTimeMapper.addTeacherTime(partTeacherTime);
    }

    @Override
    public List<PartTeacherTime> queryPartTeacherTimeByTeacherID(Integer teacherID) {
        return partTeacherTimeMapper.queryPartTeacherTimeByTeacherID(teacherID);
    }
}
