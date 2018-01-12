package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.pojo.CourseType;
import com.bdqn.huanxun.pojo.LessonType;
import com.bdqn.huanxun.service.CourseTypeService;
import com.bdqn.huanxun.service.LessonTypeService;
import com.bdqn.huanxun.service.StudentCourseService;
import com.bdqn.huanxun.tools.DataPieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2018/1/11.
 */
@Controller
@RequestMapping("/data")
public class DataController {
    @Resource
    private StudentCourseService studentCourseService;
    @Resource
    private LessonTypeService lessonTypeService;
    @Resource
    private CourseTypeService courseTypeService;

    @RequestMapping(value = "/toDataStuCourse.do",method = RequestMethod.GET)
    private String toData(){
        return "/data/dataStuCourse";
    }

    @RequestMapping(value = "/toDataStuCourse1.do",method = RequestMethod.GET)
    private String toData1(){
        return "/data/dataStuCourse1";
    }

    @RequestMapping(value = "/countStuCourseNumberByLessonTypeID.do",method = RequestMethod.GET,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String countStuCourseNumberByLessonTypeID(){
        List<LessonType> lessonTypes = lessonTypeService.findAllLessonTypes();
        List<Map<String, Object>> list = new ArrayList<>();
        if(null != lessonTypes){
            for (LessonType lessonType : lessonTypes) {
                Integer lessonTypeID = lessonType.getLessonTypeID();
                String lessonTypeDesc = lessonType.getLessonDesc();
                int count = studentCourseService.countStuCourseByLessonTypeID(lessonTypeID);
                Map<String, Object> map = new HashMap<>();
                map.put("name", lessonTypeDesc);
                map.put("value", count);
                list.add(map);
            }
        }
        return JSON.toJSONString(list);
    }

    @RequestMapping(value = "/countStuCourseNumberByCourseTypeID.do",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String countStuCourseNumberByCourseTypeID(){
        List<Map<String, Object>> list = new ArrayList<>();
        List<CourseType> courseTypes = courseTypeService.findAllCourseTypes();
        if(null!=courseTypes){
            for (CourseType courseType : courseTypes) {
                int count = studentCourseService.countStuCourseByCourseTypeID(courseType.getCourseTypeID());
                DataPieUtil.addEle(list,courseType.getCourseTypeName(),count);
            }
        }
        return JSON.toJSONString(list);
    }
}
