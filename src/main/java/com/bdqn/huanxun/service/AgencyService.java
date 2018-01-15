package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.Agency;
import com.github.pagehelper.PageInfo;

/**
 * Created by 大聪 on 2018/1/8.
 */
public interface AgencyService {

    //通过Agency查找校区员工id
    Agency findAgencyById(Integer agencyId);

    //查询全部校区信息
    PageInfo<Agency> findAll(Integer pageNum,Integer pageSize);

    //根据企业名称模糊查询
    PageInfo<Agency> findAgencyByLikeAgencyName(Integer pageNum,Integer pageSize,String agencyName);

    //详情
    Agency queryAgencyById(Integer agencyId);

    //添加
    Integer addAgency(Agency agency);

    //修改
    Integer updateAgency(Agency agency);
}
