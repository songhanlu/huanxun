package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.Employee;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hp on 2018/1/5.
 */
public interface EmployeeService {
    //通过EmployeeID查找员工
    Employee findEmployeeByID(Integer employeeID);

    /**
     * 查询所有员工的信息（分页）
     * @return
     */
    PageInfo<Employee> queryEmployee(Integer pageNum,Integer pageSize);

    /**
     * 根据用户角色查员工
     * @param userRoleId
     * @return
     */
    List<Employee> queryEmployeeByUserRoleId(Integer userRoleId);

    /**
     * 根据角色Id和员工姓名查询信息
     * @param userRoleId
     * @param employeeId
     * @return
     */
    PageInfo<Employee> queryEmployeeByUserRoleIdAndEmployeeId(Integer pageNum,Integer pageSize,
                                                           @Param("userRoleId") Integer userRoleId,
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
    Integer addEmployee(Employee employee,Integer loginUserId,String loginUserName, String loginUserPassword);

    /**
     * 修改员工
     * @param employee
     * @return
     */
    Integer updateEmployee(Employee employee,Integer loginUserId,String loginUserName, String loginUserPassword);
}
