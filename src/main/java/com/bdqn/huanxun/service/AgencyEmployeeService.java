package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.AgencyEmployee;
import com.github.pagehelper.PageInfo;

/**
 * Created by hp on 2018/1/15.
 */
public interface AgencyEmployeeService {
    //根据loginUserID查询校区员工
    AgencyEmployee findAgencyEmployeeByLoginUserID(Integer loginUserID);

    //根据agencyID查询所有该校区员工
    PageInfo<AgencyEmployee> findAEByAgencyID(Integer agencyID, Integer pageNum, Integer pageSize);
}
