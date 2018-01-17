package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.LoginUserMapper;
import com.bdqn.huanxun.pojo.LoginUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public Integer addLoginUser(LoginUser loginUser) {
        return loginUserMapper.addLoginUser(loginUser);
    }

    @Override
    public Integer updateLoginUser(LoginUser loginUser) {
        return loginUserMapper.updateLoginUser(loginUser);
    }

    @Override
    public Integer deleteLoginUser(Integer loginUserID) {
        return loginUserMapper.deleteLoginUser(loginUserID);
    }

    @Override
    public Integer deleteLoginUserByIds(List<Integer> list) {
        return loginUserMapper.deleteLoginUserByIds(list);
    }
    
}
