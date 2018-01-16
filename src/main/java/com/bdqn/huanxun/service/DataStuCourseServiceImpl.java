package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.data.DataStuCourseMapper;
import com.bdqn.huanxun.tools.DataLineUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by hp on 2018/1/16.
 */
@Service
public class DataStuCourseServiceImpl implements DataStuCourseService {
    @Resource
    private DataStuCourseMapper dataStuCourseMapper;

    @Override
    public Map<String, Object> findNumberByTimeType(String timeType) {
        if(null!=timeType && "year".equals(timeType)){
            return DataLineUtil.getLineGramData(dataStuCourseMapper.findStuCourseByYear());
        }else if(null!=timeType && "month".equals(timeType)){
            return DataLineUtil.getLineGramData(dataStuCourseMapper.findStuCourseByMonth());
        }else if(null!=timeType && "quaterYear".equals(timeType)){
            return DataLineUtil.getLineGramData(dataStuCourseMapper.findStuCourseByQuaterYear());
        }
        return null;
    }
}
