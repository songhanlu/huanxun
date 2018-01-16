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
import java.io.UnsupportedEncodingException;

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
    @RequestMapping(value = "queryLessonTypeById",method = RequestMethod.GET,
    produces = {"application/json;charset=UTF-8"})
    public String queryLessonTypeById(Integer lessonTypeID){
        LessonType lessonType1=lessonTypeService.queryLessonTypeById(lessonTypeID);
        return JSON.toJSONString(lessonType1);
    }


    @ResponseBody
    @RequestMapping(value = "lessonTypeAll.do",method = RequestMethod.GET,
            produces ={"application/json;charset=UTF-8"} )
    public String lessonTypeAll(Integer page,Integer rows,String lessonArea, Integer lessonPrice1, Integer lessonPrice2, Integer timePerLesson, String lessonDesc){
        if (lessonDesc != null) {
            try {
                lessonDesc = new String(lessonDesc.getBytes("ISO8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (lessonArea != null) {
            try {
                lessonArea = new String(lessonArea.getBytes("ISO8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        PageInfo<LessonType> pageInfo=lessonTypeService.queryAllLessonTypeByLessonType(page,rows,lessonArea,lessonPrice1,lessonPrice2,timePerLesson,lessonDesc);
        return JSON.toJSONString(new PageUtil<LessonType>(pageInfo));
    }
    @ResponseBody
    @RequestMapping(value = "updatelessonType.do",method = RequestMethod.GET,
            produces ={"application/json;charset=UTF-8"} )
    public String updatelessonType(LessonType lessonType){
        if (lessonType.getLessonDesc() != null) {
            try {
                String lessonDesc = new String(lessonType.getLessonDesc().getBytes("ISO8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (lessonType.getLessonArea() != null) {
            try {
                String lessonArea = new String(lessonType.getLessonArea().getBytes("ISO8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        int i=lessonTypeService.updateLessonDescAndLessonPriceAndTimePerLessonById(lessonType);
        if (i>0){
            return JSON.toJSONString("成功");
        }
        return JSON.toJSONString("失败");
    }


}
