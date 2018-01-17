package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.pojo.Agency;
import com.bdqn.huanxun.service.AgencyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 佳 on 2018/1/15.
 */
@Controller
@RequestMapping("agency")
public class AgencyController {
    @Resource
    private AgencyService agencyService;

    /**
     * 查询全部校区
     */
    @ResponseBody
    @RequestMapping(value = "queryAgency",method = RequestMethod.GET,
    produces = {"application/json;charset=UTF-8"})
    public String queryAgency() {
        List<Agency> list = agencyService.queryAgency();
        return JSON.toJSONString(list);
    }
}
