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
 * Created by 佳 on 2018/1/15.
 */
@Controller
@RequestMapping("studentGrade")
public class StudentGradeController {
    @Resource
    private StudentGradeService studentGradeService;

    @ResponseBody
    @RequestMapping(value = "queryStudentGrade",method = RequestMethod.GET,
    produces = {"application/json;charset=UTF-8"})
    public String queryStudentGrade() {
        List<StudentGrade> list = studentGradeService.queryStuGrade();
        return JSON.toJSONString(list);
    }

}
