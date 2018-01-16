package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.data.DataStuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2018/1/16.
 */
@Service
public class DataStuServiceImpl implements DataStuService {
    @Resource
    private DataStuMapper dataStuMapper;

    @Override
    public List<Map<String, Object>> findRegNumberByTimeType(String timeType) {
        if(null!=timeType && "year".equals(timeType)){
            return dataStuMapper.findRegNumberByYear();
        }else if(null!=timeType && "month".equals(timeType)){
            return dataStuMapper.findRegNumberByMonth();
        }else if(null!=timeType && "quaterYear".equals(timeType)){
            return dataStuMapper.findRegNumberByQuaterYear();
        }
        return null;
    }
}
