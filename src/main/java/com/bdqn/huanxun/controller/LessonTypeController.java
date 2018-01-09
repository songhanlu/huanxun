package com.bdqn.huanxun.controller;

import com.bdqn.huanxun.service.LessonTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by lenovo on 2018/1/9.
 */
@Controller
@RequestMapping(value = "lessonType")
public class LessonTypeController {
    @Resource
    private LessonTypeService lessonTypeService;


    @RequestMapping(value = "toLessonType.do")
    public String  lessonType(){
        return "lessonType/lessonType";
    }
}
