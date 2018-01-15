package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.pojo.Agency;
import com.bdqn.huanxun.service.AgencyService;
import com.bdqn.huanxun.tools.Message;
import com.bdqn.huanxun.tools.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * Created by 大聪 on 2018/1/9.
 */
@Controller
@RequestMapping("agency")
public class AgencyController {
    @Resource
    private AgencyService agencyService;

    @RequestMapping(value = "toAgency",method = RequestMethod.GET)
    public String toAgency(){
        return "agency/findAllAgency";
    }

    @ResponseBody
    @RequestMapping(value = "findAll.do", method = RequestMethod.GET,
                    produces = "application/json;charset=UTF-8")
    public String findAll(Integer page,Integer rows,String agencyName) throws UnsupportedEncodingException {
        if (agencyName != null) {
            agencyName = new String(agencyName.getBytes("ISO8859-1"), "UTF-8");
        }
        PageInfo<Agency> pageInfo = agencyService.findAgencyByLikeAgencyName(page, rows,agencyName);
        PageUtil<Agency> pageUtil = new PageUtil<Agency>(pageInfo);
        return JSON.toJSONString(pageUtil);
    }

    @ResponseBody
    @RequestMapping(value = "queryAgencyById.do",method = RequestMethod.GET,
                    produces = "application/json;charset=UTF-8")
    public String queryAgencyById(Integer id){
        Agency agency = agencyService.queryAgencyById(id);
        return JSON.toJSONString(agency);
    }

    @ResponseBody
    @RequestMapping(value = "addAgency.do",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String addAgency(Agency agency) {
        int n = agencyService.addAgency(agency);
        if (n > 0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }

    @ResponseBody
    @RequestMapping(value = "updateAgency.do",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String updateAgency(Agency agency) {
        int n = agencyService.updateAgency(agency);
        if (n > 0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }
}
