package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.pojo.Employee;
import com.bdqn.huanxun.service.EmployeeService;
import com.bdqn.huanxun.tools.Message;
import com.bdqn.huanxun.tools.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2018/1/5.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;

    @RequestMapping("/toEmployee.do")
    public String toEmployee(){
        return "admin/employee";
    }

    //查询所有员工信息
    @ResponseBody
    @RequestMapping(value = "queryEmployee.do",method = RequestMethod.GET,
    produces = {"application/json;charset=UTF-8"})
    public String queryEmployee(Integer page, Integer rows,
                                Integer userRoleId,Integer employeeId) {
      /*  PageInfo<Employee> pageInfo = employeeService.queryEmployee(page, rows);
        PageUtil<Employee> pageUtil = new PageUtil<>(pageInfo);
        return JSON.toJSONString(pageUtil);*/
        if (userRoleId != null && userRoleId == -1) {
            userRoleId = null;
        }
        if(employeeId!=null && employeeId == -1){
            employeeId = null;
        }
        PageInfo<Employee> pageInfo = employeeService.queryEmployeeByUserRoleIdAndEmployeeId(page, rows, userRoleId,employeeId);
        PageUtil<Employee> pageUtil = new PageUtil<>(pageInfo);
        return JSON.toJSONString(pageUtil);


    }

    //根据用户角色查员工
    @ResponseBody
    @RequestMapping(value = "queryEmployeeByUserRoleId.do",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public String queryEmployeeByUserRoleId(Integer userRoleId) {
        List<Employee> list = employeeService.queryEmployeeByUserRoleId(userRoleId);
        return JSON.toJSONString(list);
    }
    @ResponseBody
    @RequestMapping(value = "queryEmployeeByEmployeeId.do",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public String queryEmployeeByEmployeeId(Integer employeeId) {
        Employee employee = employeeService.queryEmployeeByEmployeeId(employeeId);
        return JSON.toJSONString(employee);
    }
    @ResponseBody
    @RequestMapping(value = "deleteEmployeeById.do",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String deleteEmployeeById(Integer employeeId) {
        int n = employeeService.deleteEmployeeById(employeeId);
        if (n > 0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }
    @ResponseBody
    @RequestMapping(value = "deleteEmployeeByIds.do",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String deleteEmployeeByIds(String employeeIds,String loginUserIds) {
        String[] loginUserIdArray = loginUserIds.split(",");
        String[] employeeIdArray = employeeIds.split(",");
        List<Integer> list = new ArrayList<>();
        if (loginUserIdArray != null && loginUserIdArray.length > 0) {
            for (String s : loginUserIdArray) {
                list.add(Integer.parseInt(s));
            }
        }
        if (employeeIdArray != null && employeeIdArray.length > 0) {
            for (String s : employeeIdArray) {
                list.add(Integer.parseInt(s));
            }
        }
        int n = employeeService.deleteEmployeeByIds(list);
        if (n > 0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }

    @ResponseBody
    @RequestMapping(value = "addEmployee.do",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String addEmployee(Employee employee,Integer loginUserId,String loginUserName,
                              String loginUserPassword) {
        int n = employeeService.addEmployee(employee,loginUserId,loginUserName, loginUserPassword);
        if (n > 0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }

    @ResponseBody
    @RequestMapping(value = "updateEmployee.do",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String updateEmployee(Employee employee,Integer loginUserId,String loginUserName,
                                 String loginUserPassword){
        int n = employeeService.updateEmployee(employee,loginUserId, loginUserName, loginUserPassword);
        if (n > 0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }

    @ResponseBody
    @RequestMapping(value = "deExists.do",method = RequestMethod.GET,
                    produces = "application/json;charset=UTF-8")
    public String deExists(String loginName) throws UnsupportedEncodingException {
        if (loginName!=null){
            loginName = new String(loginName.getBytes("ISO8859-1"), "UTF-8");
        }
        Employee employee = employeeService.queryLoginName(loginName);
        if(employee!=null){
            return "success";
        }
        return "error";
    }
}
