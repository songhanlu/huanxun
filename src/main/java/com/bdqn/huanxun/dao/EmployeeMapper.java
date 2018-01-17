package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hp on 2018/1/5.
 */
public interface EmployeeMapper {
    //通过EmployeeID查找员工
    Employee findEmployeeByID(Integer employeeID);

    /**
     * 查询所有员工信息
     * @return
     */
    List<Employee> queryEmployee();

    /**
     * 根据用户角色查员工
     * @param userRoleId
     * @return
     */
    List<Employee> queryEmployeeByUserRoleId(Integer userRoleId);

    /**
     * 根据角色Id和员工Id查询信息
     * @param userRoleId
     * @param employeeId
     * @return
     */
    List<Employee> queryEmployeeByUserRoleIdAndEmployeeId(@Param("userRoleId") Integer userRoleId,
                                                            @Param("employeeId") Integer employeeId);

    /**
     *根据用户Id查询详情
     * @param employeeId
     * @return
     */
    Employee queryEmployeeByEmployeeId(Integer employeeId);

    /**
     * 单条删除员工
     * @param employeeId
     * @return
     */
    Integer deleteEmployeeById(Integer employeeId);

    /**
     * 批量删除员工
     * @param list
     * @return
     */
    Integer deleteEmployeeByIds(List<Integer> list);

    /**
     * 添加员工
     * @param employee
     * @return
     */
    Integer addEmployee(Employee employee);

    /**
     * 修改员工
     * @param employee
     * @return
     */
    Integer updateEmployee(Employee employee);

    //ajax判断用户名是否存在
    Employee queryLoginName(String loginName);
}
