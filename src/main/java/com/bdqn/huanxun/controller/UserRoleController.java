package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.pojo.UserRole;
import com.bdqn.huanxun.service.UserRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 佳 on 2018/1/9.
 */
@Controller
@RequestMapping("userRole")
public class UserRoleController {
    @Resource
    private UserRoleService userRoleService;

    //查询所有的用户角色信息
    @ResponseBody
    @RequestMapping(value = "queryUserRole.do",method = RequestMethod.GET,
    produces = {"application/json;charset=UTF-8"})
    public String queryUserRole() {
        List<UserRole> list = userRoleService.queryUserRole();
        return JSON.toJSONString(list);
    }
}
