package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.pojo.PartTeacherTime;
import com.bdqn.huanxun.service.PartTeacherTimeService;
import com.bdqn.huanxun.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/1/16/016.
 */
@Controller
@RequestMapping("teacherTime")
public class TeacherTimeController {
    @Resource
    private PartTeacherTimeService partTeacherTimeService;
    @Resource
    private TeacherService teacherService;
    @ResponseBody
    @RequestMapping(value = "queryByTeacherID", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public String queryPartTeacherTimeByTeacherID(Integer teacherID, Model model) {
        List<PartTeacherTime> timeList = partTeacherTimeService.queryPartTeacherTimeByTeacherID(teacherID);
        model.addAttribute("timeList", timeList);
        return JSON.toJSONString(timeList);
    }
}
