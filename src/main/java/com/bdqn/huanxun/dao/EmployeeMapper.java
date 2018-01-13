package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.Employee;

/**
 * Created by hp on 2018/1/5.
 */
public interface EmployeeMapper {
    //通过EmployeeID查找员工
    Employee findEmployeeByID(Integer employeeID);


}
