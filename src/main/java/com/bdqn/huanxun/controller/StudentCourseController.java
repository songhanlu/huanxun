package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.pojo.StudentCourse;
import com.bdqn.huanxun.service.StuCourseBookService;
import com.bdqn.huanxun.service.StudentCourseService;
import com.bdqn.huanxun.tools.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2018/1/8.
 */
@Controller
@RequestMapping("/studentCourse")
public class StudentCourseController {
    @Resource
    private StudentCourseService studentCourseService;
    @Resource
    private StuCourseBookService stuCourseBookService;

    @RequestMapping("/findAllStuCourse.do")
    public String findAllStuCourse(){
        return "admin/showAddStuCourse";
    }

    @RequestMapping(value = "/addStuCourse.do",method = RequestMethod.POST,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addStuCourse(StudentCourse studentCourse){
        int result = studentCourseService.addStudentCourse(studentCourse);
        if(result>0){
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.failed());
    }

    @RequestMapping(value = "/toUploadBook.do", method = RequestMethod.GET)
    public String toUploadBook(Integer stuCourseID, Model model) {
        StudentCourse studentCourse = studentCourseService.findStudentCourseByID(stuCourseID);
        model.addAttribute("stuCourse", studentCourse);
        return "admin/uploadBookForArrange";
    }

    @RequestMapping(value = "/findBooksBystuCourseID.do",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String findBooksByCourseTypeID(Integer stuCourseID,
                                          Integer page,
                                          Integer rows){
        return JSON.toJSONString(stuCourseBookService.findStuCourseBooks(stuCourseID,page,rows));
    }

    @RequestMapping(value = "/findBooksCanBeChose.do",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String findBooksCanByChoose(Integer courseTypeID){
        return JSON.toJSONString(stuCourseBookService.findBooksByStuCourseID(courseTypeID));
    }

    @RequestMapping(value = "/doUploadBooks.do",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String doUploadBooks(Integer startBookNo,
                                Integer endBookNo,
                                Integer courseTypeID,
                                Integer stuCourseID){
        boolean result = stuCourseBookService.addBooks(stuCourseID, courseTypeID, startBookNo, endBookNo);
        if(result){
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.failed());
    }

    @RequestMapping(value = "/deleteUploadBooks.do", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String deleteUploadBooks(String IDs) {
        String[] ids = IDs.split(",");
        List<Integer> ddd = new ArrayList<>();
        for (String id : ids) {
            ddd.add(Integer.parseInt(id));
        }
        Integer result = stuCourseBookService.deleteUploadBookByIDs(ddd);
        if(result>0){
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.failed());
    }

    @RequestMapping(value = "/updateStuCourseFinished.do",method = RequestMethod.POST,
                produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String updateStuCourseFinished(String IDs){
        Integer result = studentCourseService.updateStuCourseStatusToFinished(IDs);
        if(result>0){
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.failed());
    }

    @RequestMapping(value = "/updateStuCourseActived.do",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String updateStuCourseActived(String IDs){
        Integer result = studentCourseService.updateStuCourseStatusToActive(IDs);
        if(result>0){
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.failed());
    }
}
