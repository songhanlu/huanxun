package com.bdqn.huanxun.controller;

import com.bdqn.huanxun.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by hp on 2018/1/5.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;

    @RequestMapping("/toEmployee.html")
    public String toEmployee(){
        return "admin/employee";
    }
}
