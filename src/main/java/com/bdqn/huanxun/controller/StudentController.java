package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.pojo.Student;
import com.bdqn.huanxun.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2018/1/5.
 */
@Controller
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentService studentService;

    @RequestMapping("/toStudent.do")
    public String toStudent(){
        return "admin/student";
    }

    @RequestMapping(value = "/findStuByGradeID", method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String findStuByGradeID(Integer stuGradeID) {
        List<Student> students = studentService.findfStudentSByGradeID(stuGradeID);
        return JSON.toJSONString(students);
    }
}
