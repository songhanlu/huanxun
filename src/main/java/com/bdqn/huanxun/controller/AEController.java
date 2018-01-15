package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.pojo.AgencyEmployee;
import com.bdqn.huanxun.service.AgencyEmployeeService;
import com.bdqn.huanxun.tools.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by hp on 2018/1/15.
 */
@Controller
@RequestMapping("/ae")
public class AEController {
    @Resource
    private AgencyEmployeeService agencyEmployeeService;

    @RequestMapping("/toAE")
    public String toAE(){
        return "ae/ae";
    }

    @RequestMapping(value = "/aeList.do",method = RequestMethod.GET,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String aeList(Integer agencyID,
                         Integer page,
                         Integer rows) {
        PageInfo<AgencyEmployee> pageInfo = agencyEmployeeService.findAEByAgencyID(agencyID,page,rows);
        return JSON.toJSONString(new PageUtil<AgencyEmployee>(pageInfo));
    }
}
