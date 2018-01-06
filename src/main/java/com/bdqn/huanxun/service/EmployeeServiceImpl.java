package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.EmployeeMapper;
import com.bdqn.huanxun.pojo.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hp on 2018/1/5.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public Employee findEmployeeByID(Integer employeeID) {
        return employeeMapper.findEmployeeByID(employeeID);
    }
}
