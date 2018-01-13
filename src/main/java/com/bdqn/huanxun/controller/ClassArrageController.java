package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.pojo.StudentCourse;
import com.bdqn.huanxun.service.StudentCourseService;
import com.bdqn.huanxun.tools.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by hp on 2018/1/7.
 */
@Controller
@RequestMapping("/classArrange")
public class ClassArrageController {
    @Resource
    private StudentCourseService studentCourseService;

    @RequestMapping("/toClassArrange.do")
    public String toClassArrage(){
        return "admin/classArrange";
    }

    //查询未排课的学生购买课程
    @RequestMapping(value = "/findStudentCourseIsNotArrage.do",method = RequestMethod.GET,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String findStudentCourseIsNotArrage(Integer page, Integer rows,
                                               StudentCourse studentCourse){
        studentCourse.setIsArranged(-1);
        PageInfo<StudentCourse> pageInfo = studentCourseService.findAllStudentCourse(page, rows, studentCourse);
        return JSON.toJSONString(new PageUtil<StudentCourse>(pageInfo));
    }

    @RequestMapping("/addClassArrange.do")
    public String addClassArrange(Integer stuCourseID, Model model){
        StudentCourse studentCourse = studentCourseService.findStudentCourseByID(stuCourseID);
        model.addAttribute("studentCourse",studentCourse);
        return "admin/addClassArrange";
    }
}
