package com.bdqn.huanxun.service;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2018/1/16.
 */
public interface DataStuService {
    List<Map<String, Object>> findRegNumberByTimeType(String timeType);
}
