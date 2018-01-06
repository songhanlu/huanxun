package com.bdqn.huanxun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hp on 2018/1/5.
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @RequestMapping("/toStudent.html")
    public String toStudent(){
        return "admin/student";
    }
}
