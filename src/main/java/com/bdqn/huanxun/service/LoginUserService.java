package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.LoginUser;

/**
 * Created by hp on 2018/1/4.
 */
public interface LoginUserService {
    //登录
    LoginUser login(LoginUser loginUser);

    //添加一个登录者
    Integer addLoginUser(LoginUser loginUser);

    //修改登录
    Integer updateLoginUser(LoginUser loginUser);
}
