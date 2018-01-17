package com.bdqn.huanxun.redis.redis_service;

import com.bdqn.huanxun.redis.redis_pojo.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2018/1/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AreaRedisServiceImplTest {
    @Autowired
    private AreaRedisService areaRedisService;

    @Test
    public void findAreaByParentID() throws Exception {
        System.out.println(areaRedisService.findAreaByParentID(0));
    }


    @Test
    public void addArea() throws Exception {
        List<Area> areas = new ArrayList<>();
        Area area1=new Area();
        area1.setParentID(0);
        area1.setAreaID(1);
        area1.setLevel(1);
        area1.setName("北京");
        Area area2=new Area();
        area2.setParentID(0);
        area2.setAreaID(3);
        area2.setLevel(1);
        area2.setName("上海");
        areas.add(area1);
        areas.add(area2);
        areaRedisService.addArea(areas);
    }

}