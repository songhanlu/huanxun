package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.AgencyMapper;
import com.bdqn.huanxun.pojo.Agency;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 佳 on 2018/1/13.
 */
@Service
public class AgencyServiceImpl implements AgencyService {
    @Resource
    private AgencyMapper agencyMapper;
    @Override
    public Integer updateAgencyByStuNumber(Integer stuNumber, Integer agencyID) {
        return agencyMapper.updateAgencyByStuNumber(stuNumber, agencyID);
    }

    @Override
    public Integer updateAgencyByStuNumberList(Integer stuNumber, List<Integer> list) {
        return agencyMapper.updateAgencyByStuNumberList(stuNumber, list);
    }

    @Override
    public List<Agency> queryAgency() {
        return agencyMapper.queryAgency();
    }

    @Override
    public Agency queryAgencyById(Integer agencyId) {
        return agencyMapper.queryAgencyById(agencyId);
    }

}
