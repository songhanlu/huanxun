package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.pojo.StudentCourse;
import com.bdqn.huanxun.service.StudentCourseService;
import com.bdqn.huanxun.tools.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by hp on 2018/1/8.
 */
@Controller
@RequestMapping("/studentCourse")
public class StudentCourseController {
    @Resource
    private StudentCourseService studentCourseService;

    @RequestMapping("/findAllStuCourse.do")
    public String findAllStuCourse(){
        return "admin/showAddStuCourse";
    }

    @RequestMapping(value = "/addStuCourse.do",method = RequestMethod.POST,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addStuCourse(StudentCourse studentCourse){
        int result = studentCourseService.addStudentCourse(studentCourse);
        if(result>0){
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.failed());
    }
}
