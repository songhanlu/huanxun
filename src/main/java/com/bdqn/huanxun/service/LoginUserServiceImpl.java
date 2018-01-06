package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.LoginUserMapper;
import com.bdqn.huanxun.pojo.LoginUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hp on 2018/1/4.
 */
@Service
public class LoginUserServiceImpl implements LoginUserService {
    @Resource
    private LoginUserMapper loginUserMapper;

    @Override
    public LoginUser login(LoginUser loginUser) {
        return loginUserMapper.login(loginUser);
    }
}
