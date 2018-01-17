package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.pojo.Student;
import com.bdqn.huanxun.service.StudentService;
import com.bdqn.huanxun.tools.Message;
import com.bdqn.huanxun.tools.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

    @ResponseBody
    @RequestMapping(value = "queryStudent.do",method = RequestMethod.GET,
    produces = {"application/json;charset=UTF-8"})
    public String queryStudent(Integer page,Integer rows, String stuName, String agencyName, Integer stuAgeMin, Integer stuAgeMax) throws UnsupportedEncodingException {
        if (stuName != null) {
            stuName = new String(stuName.getBytes("ISO8859-1"), "UTF-8");
        }
        if (agencyName != null) {
            agencyName = new String(agencyName.getBytes("ISO8859-1"), "UTF-8");
        }
        PageInfo<Student> pageInfo = studentService.queryStudentByNameAndAgencyAndAge(page, rows, stuName, agencyName, stuAgeMin, stuAgeMax);
        PageUtil<Student> pageUtil = new PageUtil<>(pageInfo);
        return JSON.toJSONString(pageUtil);
    }

    @ResponseBody
    @RequestMapping(value = "addStudent.do",method = RequestMethod.POST,
    produces = {"application/json;charset=UTF-8"})
    public String addStudent(Student student) {
        int n = studentService.addStudent(student);
        if (n > 0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }
    @ResponseBody
    @RequestMapping(value = "updateStudent.do",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String updateStudent(Student student) {
        int n = studentService.updateStudent(student);
        if (n > 0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }
    @ResponseBody
    @RequestMapping(value = "queryStudentByStuId.do",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public String queryStudentByStuId(Integer stuId) {
        Student student = studentService.queryStudentByStuId(stuId);
        return JSON.toJSONString(student);
    }
    @ResponseBody
    @RequestMapping(value = "deleteStudentById.do",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String deleteStudentById(Integer stuId) {
        int n = studentService.deleteStudentById(stuId);
        if (n > 0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }

    @ResponseBody
    @RequestMapping(value = "deleteStudentByList.do",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String deleteStudentByList(String stuIds) {
        String[] stuIdArray = stuIds.split(",");
        List<Integer> list = new ArrayList<>();
        if (stuIdArray != null && stuIdArray.length > 0) {
            for (String s : stuIdArray) {
                list.add(Integer.parseInt(s));
            }
        }
        int n = studentService.deleteStudentByList(list);
        if (n > 0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());

    }

}
