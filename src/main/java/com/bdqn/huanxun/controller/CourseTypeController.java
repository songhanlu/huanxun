package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.service.CourseTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by hp on 2018/1/9.
 */
@Controller
@RequestMapping("/courseType")
public class CourseTypeController {
    @Resource
    private CourseTypeService courseTypeService;

    @RequestMapping(value = "/findAllCourseType",method = RequestMethod.GET,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String findAllCourseType(){
        return JSON.toJSONString(courseTypeService.findAllCourseTypes());
    }
}
