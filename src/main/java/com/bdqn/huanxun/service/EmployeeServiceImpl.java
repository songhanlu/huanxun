package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.EmployeeMapper;
import com.bdqn.huanxun.dao.LoginUserMapper;
import com.bdqn.huanxun.pojo.Employee;
import com.bdqn.huanxun.pojo.LoginUser;
import com.bdqn.huanxun.pojo.UserRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2018/1/5.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private LoginUserMapper loginUserMapper;

    @Override
    public Employee findEmployeeByID(Integer employeeID) {
        return employeeMapper.findEmployeeByID(employeeID);
    }

    @Override
    public PageInfo<Employee> queryEmployee(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Employee> list = employeeMapper.queryEmployee();
        PageInfo<Employee> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<Employee> queryEmployeeByUserRoleId(Integer userRoleId) {
        return employeeMapper.queryEmployeeByUserRoleId(userRoleId);
    }

    @Override
    public PageInfo<Employee> queryEmployeeByUserRoleIdAndEmployeeId(Integer pageNum, Integer pageSize,
                                                                       Integer userRoleId,Integer employeeId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Employee> list = employeeMapper.queryEmployeeByUserRoleIdAndEmployeeId(userRoleId,employeeId);
        PageInfo<Employee> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Employee queryEmployeeByEmployeeId(Integer employeeId) {
        return employeeMapper.queryEmployeeByEmployeeId(employeeId);
    }

    @Override
    public Integer deleteEmployeeById(Integer employeeId) {
        Employee employee = employeeMapper.queryEmployeeByEmployeeId(employeeId);
        loginUserMapper.deleteLoginUser(employee.getLoginUserID());
        return employeeMapper.deleteEmployeeById(employeeId);
    }

    @Override
    public Integer deleteEmployeeByIds(List<Integer> list) {
        loginUserMapper.deleteLoginUserByIds(list);
        return employeeMapper.deleteEmployeeByIds(list);
    }

    @Override
    public Integer addEmployee(Employee employee,Integer loginUserId,String loginUserName,
                               String loginUserPassword) {
        LoginUser loginUser = new LoginUser();
        UserRole userRole = new UserRole();
        userRole.setUserRoleID(employee.getLoginUser().getUserRole().getUserRoleID());
        loginUser.setLoginName(loginUserName);
        loginUser.setLoginUserID(loginUserId);
        loginUser.setLoginPassword(loginUserPassword);
        loginUser.setUserRole(userRole);
        //添加登录者
        loginUserMapper.addLoginUser(loginUser);

        //获取登录id
        Integer loginUserID = loginUser.getLoginUserID();
        employee.setLoginUserID(loginUserID);
        //添加员工
        return employeeMapper.addEmployee(employee);
    }

    @Override
    public Integer updateEmployee(Employee employee,Integer loginUserId,String loginUserName, String loginUserPassword) {
        LoginUser loginUser = new LoginUser();
        UserRole userRole = new UserRole();
        userRole.setUserRoleID(employee.getLoginUser().getUserRole().getUserRoleID());
        loginUser.setLoginName(loginUserName);
        loginUser.setLoginUserID(loginUserId);
        loginUser.setLoginPassword(loginUserPassword);
        loginUser.setUserRole(userRole);
        //添加登录者
        loginUserMapper.updateLoginUser(loginUser);

        //获取登录id
        Integer loginUserID = loginUser.getLoginUserID();
        employee.setLoginUserID(loginUserID);
        //添加员工
        return employeeMapper.updateEmployee(employee);
    }

    @Override
    public Employee queryLoginName(String loginName) {
        return employeeMapper.queryLoginName(loginName);
    }

}
