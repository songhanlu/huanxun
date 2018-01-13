package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.pojo.Student;
import com.bdqn.huanxun.service.StudentService;
import com.bdqn.huanxun.tools.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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

    @ResponseBody
    @RequestMapping(value = "queryStudent.do",method = RequestMethod.GET,
    produces = {"application/json;charset=UTF-8"})
    public String queryStudent(Integer page,Integer rows) {
        PageInfo<Student> pageInfo = studentService.queryStudent(page, rows);
        PageUtil<Student> pageUtil = new PageUtil<>(pageInfo);
        return JSON.toJSONString(pageUtil);
    }
    @RequestMapping("/toQueryStudentByNameAndAgencyAndAge.do")
    public String toQueryStudentByNameAndAgencyAndAge() {
        return "admin/queryStudent";
    }
    @ResponseBody
    @RequestMapping(value = "queryStudentByNameAndAgencyAndAge.do",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public String queryStudentByNameAndAgencyAndAge(Integer page, Integer row, String stuName, String agencyName, Integer stuAgeMin, Integer stuAgeMax) {
        PageInfo<Student> pageInfo = studentService.queryStudentByNameAndAgencyAndAge(page, row, stuName, agencyName, stuAgeMin, stuAgeMax);
        PageUtil<Student> pageUtil = new PageUtil<>(pageInfo);
        return JSON.toJSONString(pageUtil);
    }
}
