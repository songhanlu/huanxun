package com.bdqn.huanxun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hp on 2018/1/8.
 */
@Controller
@RequestMapping("/studentCourse")
public class StudentCourseController {

    @RequestMapping("/findAllStuCourse.do")
    public String findAllStuCourse(){
        return "admin/showAddStuCourse";
    }
}
