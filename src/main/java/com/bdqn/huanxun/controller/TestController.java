package com.bdqn.huanxun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hp on 2018/1/4.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/test.do")
    public String test() {
        return "admin/index";
    }
}
