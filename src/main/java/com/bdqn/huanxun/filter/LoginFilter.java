package com.bdqn.huanxun.filter;

import com.bdqn.huanxun.pojo.LoginUser;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by hp on 2018/1/4.
 */
public class LoginFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        String contextPath = httpServletRequest.getContextPath();
        LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
        if(null==loginUser){
            httpServletResponse.sendRedirect(contextPath+"/");
        }else{
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }
}
