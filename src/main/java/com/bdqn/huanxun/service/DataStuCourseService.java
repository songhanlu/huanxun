package com.bdqn.huanxun.service;

import java.util.Map;

/**
 * Created by hp on 2018/1/16.
 */
public interface DataStuCourseService {
    //根据年月日查询新报名课统计数
    Map<String,Object> findNumberByTimeType(String timeType);
}
