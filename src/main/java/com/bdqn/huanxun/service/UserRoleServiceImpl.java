package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.UserRoleMapper;
import com.bdqn.huanxun.pojo.UserRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 佳 on 2018/1/9.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;
    @Override
    public List<UserRole> queryUserRole() {
        return userRoleMapper.queryUserRole();
    }

}
