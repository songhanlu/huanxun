package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.AgencyEmployee;

import java.util.List;

/**
 * Created by hp on 2018/1/15.
 */
public interface AgencyEmployeeMapper {
    //根据loginUserID查询校区员工
    AgencyEmployee findAgencyEmployeeByLoginUserID(Integer loginUserID);

    //根据agencyID查询所有该校区员工
    List<AgencyEmployee> findAEByAgencyID(Integer agencyID);

}
