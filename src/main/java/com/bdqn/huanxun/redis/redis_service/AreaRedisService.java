package com.bdqn.huanxun.redis.redis_service;

import com.bdqn.huanxun.redis.redis_pojo.Area;

import java.util.List;

/**
 * Created by hp on 2018/1/16.
 */
public interface AreaRedisService {
    void addArea(List<Area> areas);

    List<String> findAreaByParentID(Integer parentID);
}
