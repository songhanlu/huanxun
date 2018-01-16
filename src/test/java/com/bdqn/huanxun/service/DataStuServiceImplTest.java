package com.bdqn.huanxun.service;

import com.bdqn.huanxun.tools.DataLineUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2018/1/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DataStuServiceImplTest {
    @Resource
    private DataStuService dataStuService;

    @Test
    public void findRegNumberByTimeType() throws Exception {
        List<Map<String, Object>> maps = dataStuService.findRegNumberByTimeType("quaterYear");
        if(null!=maps){
            System.out.println(maps);
        }

        List<Map<String, Object>> xArray = DataLineUtil.getXArray(maps);
        List<Object> yArray = DataLineUtil.getYArray(maps);
        System.out.println(xArray);
        System.out.println(yArray);
    }

}