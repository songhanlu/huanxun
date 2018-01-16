package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.Agency;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 大聪 on 2018/1/8.
 */
public interface AgencyMapper {

    //通过Agency查找校区员工id
    Agency findAgencyById(Integer agencyId);

    //查询全部
    List<Agency> findAll();

    //根据企业名称模糊查询
    List<Agency> findAgencyByLikeAgencyName(@Param("agencyName") String agencyName);

    //详情
    Agency queryAgencyById(Integer agencyId);

    //添加
    Integer addAgency(Agency agency);

    //修改
    Integer updateAgency(Agency agency);

    //ajax判断机构名称是否存在
    Agency queryAgencyName(String agencyName);
}
