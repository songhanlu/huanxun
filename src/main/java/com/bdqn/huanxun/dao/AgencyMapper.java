package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.Agency;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 大聪 on 2018/1/8.
 */
public interface AgencyMapper {
    //根据ID修改校区人数
    public Integer updateAgencyByStuNumber(@Param("stuNumber") Integer stuNumber,
                                           @Param("agencyID") Integer agencyID);

    //根据ID批量修改校区人数
    public Integer updateAgencyByStuNumberList(@Param("stuNumber") Integer stuNumber,
                                               @Param("list") List<Integer> list);
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

    //查询全部校区
    public List<Agency> queryAgency();

    /*//删除
    Integer deleteAgency(List<Integer> ids);*/
}
