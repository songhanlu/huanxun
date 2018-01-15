package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.AgencyMapper;
import com.bdqn.huanxun.dao.LoginUserMapper;
import com.bdqn.huanxun.pojo.Agency;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 大聪 on 2018/1/8.
 */
@Service
public class AgencyServiceImpl implements AgencyService {
    @Resource
    private AgencyMapper agencyMapper;
    @Resource
    private LoginUserMapper loginUserMapper;
    @Override
    public Agency findAgencyById(Integer agencyId) {
        return agencyMapper.findAgencyById(agencyId);
    }

    @Override
    public PageInfo<Agency> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Agency> list = agencyMapper.findAll();
        return new PageInfo<Agency>(list);
    }

    @Override
    public PageInfo<Agency> findAgencyByLikeAgencyName(Integer pageNum, Integer pageSize, String agencyName) {
        PageHelper.startPage(pageNum, pageSize);
        List<Agency> list = agencyMapper.findAgencyByLikeAgencyName(agencyName);
        return new PageInfo<Agency>(list);
    }


    @Override
    public Agency queryAgencyById(Integer agencyId) {
        return agencyMapper.queryAgencyById(agencyId);
    }

    @Override
    public Integer addAgency(Agency agency, Integer loginUserID, String loginName, String loginPassword) {
        return agencyMapper.addAgency(agency);
    }

    @Override
    public Integer updateAgency(Agency agency, Integer loginUserID, String loginName, String loginPassword) {
        return agencyMapper.updateAgency(agency);
    }


}