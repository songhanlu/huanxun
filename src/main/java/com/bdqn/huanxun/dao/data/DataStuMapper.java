package com.bdqn.huanxun.dao.data;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2018/1/16.
 */
public interface DataStuMapper {
    /**
     * 按年，查询新注册学生的数量
     * @return
     */
    List<Map<String, Object>> findRegNumberByYear();

    /**
     * 按月，查询新注册学生的数量
     * @return
     */
    List<Map<String, Object>> findRegNumberByMonth();

    /**
     * 按季度，查询新注册学生的数量
     * @return
     */
    List<Map<String, Object>> findRegNumberByQuaterYear();
}
