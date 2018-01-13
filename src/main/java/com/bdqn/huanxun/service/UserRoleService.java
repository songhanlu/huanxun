package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.UserRole;

import java.util.List;

/**
 * Created by 佳 on 2018/1/9.
 */
public interface UserRoleService {
    /**
     * 查询所有的的用户信息
     * @return
     */
    List<UserRole> queryUserRole();
}
