package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.LoginUser;

/**
 * Created by hp on 2018/1/4.
 */
public interface LoginUserMapper {
    //登录
    LoginUser login(LoginUser loginUser);
}
