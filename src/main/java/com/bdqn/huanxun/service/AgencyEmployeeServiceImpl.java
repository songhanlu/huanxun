package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.AgencyEmployeeMapper;
import com.bdqn.huanxun.dao.LoginUserMapper;
import com.bdqn.huanxun.pojo.AgencyEmployee;
import com.bdqn.huanxun.pojo.LoginUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2018/1/15.
 */
@Service
public class AgencyEmployeeServiceImpl implements AgencyEmployeeService {
    @Resource
    private AgencyEmployeeMapper agencyEmployeeMapper;
    @Resource
    private LoginUserMapper loginUserMapper;

    @Override
    public AgencyEmployee findAgencyEmployeeByLoginUserID(Integer loginUserID) {
        return agencyEmployeeMapper.findAgencyEmployeeByLoginUserID(loginUserID);
    }

    @Override
    public PageInfo<AgencyEmployee> findAEByAgencyID(Integer agencyID,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AgencyEmployee> agencyEmployees = agencyEmployeeMapper.findAEByAgencyID(agencyID);
        return new PageInfo<>(agencyEmployees);
    }

    @Override
    public Integer addAE(AgencyEmployee agencyEmployee) {
        LoginUser loginUser = agencyEmployee.getLoginUser();
        Integer r1 = loginUserMapper.addLoginUser(loginUser);
        agencyEmployee.setLoginUser(loginUser);
        Integer r2 = agencyEmployeeMapper.addAE(agencyEmployee);
        return (r1>0 && r2>0)?1:0;
    }
}
