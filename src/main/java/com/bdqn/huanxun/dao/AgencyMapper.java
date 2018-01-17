package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.Agency;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 佳 on 2018/1/13.
 */
public interface AgencyMapper {
    //根据ID修改校区人数
    public Integer updateAgencyByStuNumber(@Param("stuNumber") Integer stuNumber,
                                           @Param("agencyID") Integer agencyID);

    //根据ID批量修改校区人数
    public Integer updateAgencyByStuNumberList(@Param("stuNumber") Integer stuNumber,
                                               @Param("list") List<Integer> list);

    //查询全部校区
    public List<Agency> queryAgency();
    //根据ID查询校区详情
    Agency queryAgencyById(Integer agencyId);


}
