package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;

import com.bdqn.huanxun.pojo.StudentCourse;
import com.bdqn.huanxun.service.StudentCourseService;
import com.bdqn.huanxun.pojo.*;
import com.bdqn.huanxun.service.ClassBookService;
import com.bdqn.huanxun.service.StudentClassArrangeService;
import com.bdqn.huanxun.service.StudentCourseService;
import com.bdqn.huanxun.tools.Message;
import com.bdqn.huanxun.tools.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by hp on 2018/1/7.
 */
@Controller
@RequestMapping("/classArrange")
public class ClassArrageController {
    @Resource
    private StudentCourseService studentCourseService;
    @Resource
    private StudentClassArrangeService studentClassArrangeService;
    @Resource
    private ClassBookService classBookService;

    @RequestMapping("/toClassArrange.do")
    public String toClassArrage(){
        return "admin/classArrange";
    }

    //查询所有学生购买课程h
    @RequestMapping(value = "/findStudentCourseIsNotArrage.do",method = RequestMethod.GET,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String findStudentCourseIsNotArrage(Integer page, Integer rows,
                                               StudentCourse studentCourse,
                                               String stuName,
                                               Integer isArranged,
                                               Integer courseTypeID,
                                               Integer lessonTypeID,
                                               String sort,
                                               String order) throws UnsupportedEncodingException {
        //studentCourse.setIsArranged(-1);//-1为所有课程
        if(null!=stuName){
            stuName = new String(stuName.getBytes("ISO8859-1"),"UTF-8");
        }
        if(null!= courseTypeID && courseTypeID==-1){
            courseTypeID=null;
        }
        if(null!=lessonTypeID && lessonTypeID==-1){
            lessonTypeID=null;
        }
        PageInfo<StudentCourse> pageInfo = studentCourseService.findAllStudentCourse(page, rows, studentCourse,stuName,isArranged,courseTypeID,lessonTypeID,sort,order);
        return JSON.toJSONString(new PageUtil<StudentCourse>(pageInfo));
    }

    //进入新增学生购买课程页面
    @RequestMapping("/toAddStuCourse.do")
    public String toAddStuCourse(){
        return "admin/showAddStuCourse";
    }

    @RequestMapping("/addClassArrange.do")
    public String addClassArrange(Integer stuCourseID, Model model) {
        StudentCourse studentCourse = studentCourseService.findStudentCourseByID(stuCourseID);

        model.addAttribute("studentCourse", studentCourse);
        model.addAttribute("classArrangeTotal", studentClassArrangeService.countClassArrange(stuCourseID));
        return "admin/addClassArrange";
    }

    //根据stuCourseID查询课表课表
    @RequestMapping(value = "/findArrangesByCourseID.do", method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String findArrangesByCourseID(Integer page, Integer rows, Integer stuCourseID) {
        PageInfo<StudentClassArrange> pageInfo = studentClassArrangeService.findstuClassArranges(page, rows, stuCourseID);
        return JSON.toJSONString(new PageUtil<StudentClassArrange>(pageInfo));
    }

    //添加课表
    @RequestMapping(value = "/addArrange.do", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addArrange(Integer times,
                             Integer stuCourseID,
                             String startTime,
                             String endTime,
                             Integer lessonNumber,
                             Integer teacherID) throws ParseException {
        Teacher teacher = new Teacher();
        teacher.setTeacherID(teacherID);
        Date sTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startTime);
        Date eTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(endTime);
        //判断外教时间是否冲突
        boolean isDupe = studentClassArrangeService.teacherTimeIsNotDupe(times, teacherID, sTime, eTime,-1);
        if(!isDupe){
            return JSON.toJSONString(Message.teacherTimeDupeError());
        }
        StudentClassArrange studentClassArrange = new StudentClassArrange();
        studentClassArrange.setStuCourseID(stuCourseID);
        studentClassArrange.setStartTime(sTime);
        studentClassArrange.setEndTime(eTime);
        studentClassArrange.setLessonNumber(lessonNumber);
        studentClassArrange.setTeacher(teacher);

        StudentCourse studentCourse = studentCourseService.findStudentCourseByID(stuCourseID);

        //开始排课
        boolean success = true;
        Date dateSSS = sTime;
        Date dateEEE = eTime;
        for(int i = 0; i < times; i++){
            studentClassArrange.setStartTime(dateSSS);
            studentClassArrange.setEndTime(dateEEE);
            int result = studentClassArrangeService.addStuClassArrange(studentClassArrange);
            if(result>0){
                //判断是否完成排课
                Integer countArrange = studentClassArrangeService.countClassArrange(stuCourseID);
                int totalLessonNumber = studentCourse.getLessonTotalNumber();
                if(null!=countArrange && countArrange>=totalLessonNumber){
                    studentCourse.setIsArranged(1);
                    studentCourseService.updateStudentCourse(studentCourse);
                }
                //时间推算到下一周
                Calendar calendarSSS = Calendar.getInstance();
                //calendarSSS.set(dateSSS.getYear(), dateSSS.getMonth(), dateSSS.getDate(), dateSSS.getHours(), dateSSS.getMinutes(), dateSSS.getSeconds());
                calendarSSS.setTime(dateSSS);
                calendarSSS.add(Calendar.DAY_OF_WEEK,7);
                dateSSS = calendarSSS.getTime();

                Calendar calendarEEE = Calendar.getInstance();
                //calendarEEE.set(dateEEE.getYear(), dateEEE.getMonth(), dateEEE.getDate(), dateEEE.getHours(), dateEEE.getMinutes(), dateEEE.getSeconds());
                calendarEEE.setTime(dateEEE);
                calendarEEE.add(Calendar.DAY_OF_WEEK,7);
                dateEEE = calendarEEE.getTime();

            }else{
                success = false;
                break;
            }
        }
        if(success){
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.failed());

    }

    //删除studentClassArrange
    @RequestMapping(value = "/deleteArrange.do", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String deleteArrange(String IDs,Integer stuCourseID){
        int result = studentClassArrangeService.deleteClassArrange(IDs);
        if(result>0){
            StudentCourse studentCourse = studentCourseService.findStudentCourseByID(stuCourseID);
            int totalLessonNumber = studentCourse.getLessonTotalNumber();
            Integer countArrange = studentClassArrangeService.countClassArrange(stuCourseID);
            if(null==countArrange){
                countArrange = 0;
            }
            if(null!=countArrange && countArrange<totalLessonNumber){
                studentCourse.setIsArranged(0);
                studentCourseService.updateStudentCourse(studentCourse);
            }
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.failed());
    }

    //根据stuClassArrangeID查询课表
    @RequestMapping(value = "/findArrangeByID.do", method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String findArrangeByID(Integer id) {
        StudentClassArrange studentClassArrange = studentClassArrangeService.findStuClassArrangeByID(id);
        return JSON.toJSONString(studentClassArrange);
    }

    //修改课表
    @RequestMapping(value = "/updateArrange.do", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String updateArrange(Integer stuClassArrangeID,
                                String startTime,
                                String endTime,
                                Integer lesssonNumber) throws ParseException {
        Date sTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startTime);
        Date eTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(endTime);
        //判断外教时间是否冲突
        int teacherID = studentClassArrangeService.findStuClassArrangeByID(stuClassArrangeID).getTeacher().getTeacherID();
        boolean isDupe = studentClassArrangeService.teacherTimeIsNotDupe(1, teacherID, sTime, eTime, stuClassArrangeID);
        if(!isDupe){
            return JSON.toJSONString(Message.teacherTimeDupeError());
        }
        StudentClassArrange studentClassArrange = new StudentClassArrange();
        studentClassArrange.setStuClassArrangeID(stuClassArrangeID);
        studentClassArrange.setLessonNumber(lesssonNumber);
        studentClassArrange.setStartTime(sTime);
        studentClassArrange.setEndTime(eTime);
        int result = studentClassArrangeService.updateArrange(studentClassArrange);
        if(result>0){
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.failed());
    }

    //根据stuCourseID查询课程已排课数量
    @RequestMapping(value = "/countClassArrange.do", method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String countClassArrange(Integer stuCourseID){
        Integer countClassArrange = studentClassArrangeService.countClassArrange(stuCourseID);
        Map<String, Integer> map = new HashMap<>();
        if(null!=countClassArrange){
            map.put("count", countClassArrange);
        }else{
            map.put("count", 0);
        }
        return JSON.toJSONString(map);
    }

    //打开上传教材窗口，该课程已有教材
    @RequestMapping(value = "/findBooksByArrangeID.do", method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String findBooksByArrangeID(Integer arrangeID){
        List<Book> books = classBookService.findBooksByArrangeID(arrangeID);
        return JSON.toJSONString(books);
    }

    //新增上传教材窗口，显示可增加的教材列表
    @RequestMapping(value = "/findBooksByCourseTypeID.do", method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String findBooksByCourseTypeID(Integer courseTypeID) {
        List<Book> books = classBookService.findBooksByCourseTypeID(courseTypeID);
        List<Map<String, Object>> list = new ArrayList<>();
        if(null!=books){
            for (Book book : books) {
                Map<String, Object> map = new HashMap<>();
                map.put("id",book.getBookID());
                map.put("text", book.getBookTitle());
                list.add(map);
            }
        }
        return JSON.toJSONString(list);
    }

    //删除课表的教材
    @RequestMapping(value = "/deleteClassBookByArrangeID.do", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String deleteClassBookByArrangeID(Integer arrangeID) {
        int result = classBookService.deleteClassBook(arrangeID);
        if(result>0){
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.failed());
    }

    //为本条课表添加教材
    @RequestMapping(value = "/addClassBook.do", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String addClassBook(ClassBook classBook){
        int result = classBookService.addClassBook(classBook);
        if(result>0){
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.failed());
    }

    //根据课表查询教材已有列表
    @RequestMapping(value = "/findClassBookByArrangeID.do", method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String findClassBookByArrangeID(Integer arrangeID){
        List<Book> books = classBookService.findBooksByArrangeID(arrangeID);
        return JSON.toJSONString(books);
    }

}
