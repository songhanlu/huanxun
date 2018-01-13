package com.bdqn.huanxun.controller;

import com.bdqn.huanxun.pojo.Agency;
import com.bdqn.huanxun.pojo.Employee;
import com.bdqn.huanxun.pojo.LoginUser;
import com.bdqn.huanxun.service.AgencyService;
import com.bdqn.huanxun.service.EmployeeService;
import com.bdqn.huanxun.service.LoginUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by hp on 2018/1/4.
 */
@Controller
@RequestMapping("/common")
public class LoginController {
    @Resource
    private LoginUserService loginUserService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private AgencyService agencyService;
    @RequestMapping("/login")
    public String login(LoginUser loginUser, HttpSession session, Model model) {
        LoginUser loginUser1 = loginUserService.login(loginUser);
        if(loginUser1!=null){//在loginUser表中找到用户名密码
            System.out.println(loginUser1);
            int userRoleID = loginUser1.getUserRole().getUserRoleID();
            if(userRoleID==1 || userRoleID==2 || userRoleID==3 || userRoleID==4){//员工
                session.setAttribute("loginUser",loginUser1);
                //到employee表里，把当前登录用户具体信息找出来
                Employee currentEmployee = employeeService.findEmployeeByID(loginUser1.getLoginUserID());
                session.setAttribute("currentEmployee",currentEmployee);
                return "admin/index";
            }else if(userRoleID==6 || userRoleID==7){
                session.setAttribute("loginUser",loginUser1);
                Agency agency = agencyService.findAgencyById(loginUser1.getLoginUserID());
                session.setAttribute("agency",agency);
                return "admin/index";
            } else{
                model.addAttribute("loginMessage","用户身份异常！");
                return "login";
            }
        }
        model.addAttribute("loginMessage","用户名或密码错误！");
        return "login";
    }
}
