package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.pojo.StudentGrade;
import com.bdqn.huanxun.service.StudentGradeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2018/1/8.
 */
@Controller
@RequestMapping("/stuGrade")
public class StudentGradeController {
    @Resource
    private StudentGradeService studentGradeService;


    @RequestMapping(value = "/findAllStudentGrade.do",method = RequestMethod.GET,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String findAllStudentGrade(){
        List<StudentGrade> studentGrades = studentGradeService.findAllStudentGrade();
        return JSON.toJSONString(studentGrades);
    }
}
