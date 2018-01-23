package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.LoginUser;

import java.util.List;

/**
 * Created by hp on 2018/1/4.
 */
public interface LoginUserMapper {
    //登录
    LoginUser login(LoginUser loginUser);

    //添加一个登录者
    Integer addLoginUser(LoginUser loginUser);

    //修改登录
    Integer updateLoginUser(LoginUser loginUser);

    //删除登录
    Integer deleteLoginUser(Integer loginUserID);
    //批量删除登录
    Integer deleteLoginUserByIds(List<Integer> list);


    Integer updateLoginUser_cf(LoginUser loginUser);

    Integer deleteLoginUser_cf(Integer loginUserID);
    Integer deleteLoginUserList_cf(List<Integer> loginUserIDs);

    Integer addLoginUser_cf(LoginUser loginUser);
}


