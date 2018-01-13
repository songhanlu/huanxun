package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * Created by hp on 2018/1/9.
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    private TeacherService teacherService;

    @RequestMapping(value = "/findTeacherByCareerType", method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String findTeacherByCareerType(String careerType) throws UnsupportedEncodingException {
        careerType = new String(careerType.getBytes("ISO8859-1"), "UTF-8");
        return JSON.toJSONString(teacherService.findTeachersByCareerType(careerType));
    }
}
