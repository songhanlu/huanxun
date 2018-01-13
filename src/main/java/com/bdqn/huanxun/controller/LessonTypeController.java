package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.pojo.LessonType;
import com.bdqn.huanxun.service.LessonTypeService;
import com.bdqn.huanxun.tools.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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


    @ResponseBody
    @RequestMapping(value = "lessonTypeAll.do",method = RequestMethod.GET,
            produces ={"application/json;charset=UTF-8"} )
    public String lessonTypeAll(Integer page,Integer rows,String lessonArea, Integer lessonPrice1, Integer lessonPrice2, Integer timePerLesson, Integer lessonDesc){
        PageInfo<LessonType> pageInfo=lessonTypeService.queryAllLessonType(page,rows,lessonArea,lessonPrice1,lessonPrice2,timePerLesson,lessonDesc);
        return JSON.toJSONString(new PageUtil<LessonType>(pageInfo));
    }

}
