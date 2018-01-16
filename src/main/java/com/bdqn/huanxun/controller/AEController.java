package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.pojo.Agency;
import com.bdqn.huanxun.pojo.AgencyEmployee;
import com.bdqn.huanxun.service.AgencyEmployeeService;
import com.bdqn.huanxun.service.AgencyService;
import com.bdqn.huanxun.tools.Message;
import com.bdqn.huanxun.tools.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Resource
    private AgencyService agencyService;

    @RequestMapping(value = "/toAE",method = RequestMethod.GET)
    public String toAE(Integer agencyID, Model model){
        Agency agency = agencyService.queryAgencyById(agencyID);
        model.addAttribute("agency", agency);
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

    @RequestMapping(value = "/addAE.do",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addAE(AgencyEmployee ae){
        int result = agencyEmployeeService.addAE(ae);
        if(result>0){
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.failed());
    }


}
