package com.bdqn.huanxun.redis.redis_service;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.redis.redis_pojo.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hp on 2018/1/16.
 */
@Service
public class AreaRedisServiceImpl implements AreaRedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addArea(List<Area> areas) {
        ListOperations listOperations = stringRedisTemplate.opsForList();
        if(null!=areas){
            for (Area area : areas) {
                listOperations.rightPush("parentID:"+area.getParentID(),
                        JSON.toJSONString(area));
            }
        }
    }

    @Override
    public List<String> findAreaByParentID(Integer parentID) {
        return stringRedisTemplate.opsForList().range("parentID:"+parentID,0,-1);
    }
}
