package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.AgencyEmployee;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by hp on 2018/1/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AgencyEmployeeServiceImplTest {

    @Resource
    private AgencyEmployeeService agencyEmployeeService;

    @Test
    public void findAgencyEmployeeByLoginUserID() throws Exception {
        AgencyEmployee agencyEmployee = agencyEmployeeService.findAgencyEmployeeByLoginUserID(4);
        System.out.println(agencyEmployee);
    }

    @Test
    public void findAEByAgencyID() throws Exception {
        PageInfo<AgencyEmployee> pageInfo = agencyEmployeeService.findAEByAgencyID(1, 1, 10);
        for (AgencyEmployee agencyEmployee : pageInfo.getList()) {
            System.out.println(agencyEmployee);
        }
    }

}