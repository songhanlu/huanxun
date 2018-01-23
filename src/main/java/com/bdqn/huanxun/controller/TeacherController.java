package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.pojo.LoginUser;
import com.bdqn.huanxun.pojo.Teacher;
import com.bdqn.huanxun.service.LoginUserService;
import com.bdqn.huanxun.service.PartTeacherTimeService;
import com.bdqn.huanxun.service.TeacherService;
import com.bdqn.huanxun.tools.Message;
import com.bdqn.huanxun.tools.PageUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    private TeacherService teacherService;
    @Resource
    private LoginUserService loginUserService;
    @Resource
    private PartTeacherTimeService partTeacherTimeService;

    @RequestMapping(value = "/findTeacherByCareerType", method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String findTeacherByCareerType(String careerType) throws UnsupportedEncodingException {
        careerType = new String(careerType.getBytes("ISO8859-1"), "UTF-8");
        return JSON.toJSONString(teacherService.findTeachersByCareerType(careerType));
    }

    @RequestMapping("/toTeacher")
    public String toTeacher() {
        return "admin/teacher";
    }
    @ResponseBody
    @RequestMapping(value = "/queryAllTeacher",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public String queryTeacher(Integer page,Integer rows,String teacherName,String loginName) {
        PageInfo<Teacher> pageInfo = teacherService.queryAllByNameAndGender(page, rows,teacherName,loginName);
        return JSON.toJSONString(new PageUtil<Teacher>(pageInfo));
    }
    @ResponseBody
    @RequestMapping(value = "/queryTeacherById", method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    public String queryTeacherById(Integer teacherID) {
        Teacher teacher = teacherService.queryTeacherById(teacherID);
        return JSON.toJSONString(teacher);
    }
    @ResponseBody
    @RequestMapping(value = "/deleteTeacherById", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String deleteTeacherById(Integer teacherID) {
        int n = teacherService.deleteTeacherById(teacherID);
        if (n > 0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }
    @ResponseBody
    @RequestMapping(value = "/deleteTeacherByList", method = RequestMethod.POST,  produces = {"application/json;charset=UTF-8"})
    public String deleteTeacherByList(String teacherIDs) {
        String[] teacherArray = teacherIDs.split(",");
        List<Integer> list = new ArrayList<Integer>();
        if (null != teacherArray && teacherArray.length > 0) {
            for (String s : teacherArray) {
                list.add(Integer.parseInt(s));
            }
        }
        int n = teacherService.deleteTeacherList(list);
        if (n > 0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }
    @ResponseBody
    @RequestMapping(value = "/deleteTeacherTimeByList", method = RequestMethod.POST,  produces = {"application/json;charset=UTF-8"})
    public String deleteTeacherTimeByList(String partTeacherTimeIDs) {
        String[] timeArray = partTeacherTimeIDs.split(",");
        List<Integer> list = new ArrayList<Integer>();
        if (null != timeArray && timeArray.length > 0) {
            for (String s : timeArray) {
                list.add(Integer.parseInt(s));
            }
        }
        int n = partTeacherTimeService.deleteTeacherTimeList(list);
        return null;
    }
    @ResponseBody
    @RequestMapping(value = "/queryTeacherAll",method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public String queryTeacherAll() {
        List<Teacher> list=teacherService.queryTeacherAll();
        return JSON.toJSONString(list);
    }

    @ResponseBody
    @RequestMapping(value = "/updateTeacher", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String updateTeacher(Teacher teacher, HttpServletRequest request) {
        int n = teacherService.updateTeacher(teacher);
        LoginUser loginUser=new LoginUser();
        loginUser.setLoginUserID(Integer.parseInt(request.getParameter("loginUserID")));
        loginUser.setLoginName(request.getParameter("loginName"));
        loginUser.setLoginPassword(request.getParameter("loginPassword"));
        int a = loginUserService.updateLoginUser(loginUser);
        if (n > 0&& a>0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }
    @ResponseBody
    @RequestMapping(value = "updateProduct",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String updateProduct(Teacher teacher,
                                HttpSession session,
                                HttpServletRequest request,
                                @RequestParam(value = "teacherPhotoUpdate",required = false)MultipartFile attach) {
        //上传文件
        String filePath = null;
        //判断文件是否为空
        if(null!=attach && !attach.isEmpty()){
            String path = request.getSession().getServletContext().getRealPath("statics/upload");
            String oldFileName = attach.getOriginalFilename();//原文件名
            String prefix = FilenameUtils.getExtension(oldFileName);
            //判断文件大小。。todo
            //判断文件格式。。todo
            String fileName = System.currentTimeMillis()+ RandomUtils.nextInt(1000000)+"_product.jpg";
            File targetFile = new File(path, fileName);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            try {
                attach.transferTo(targetFile);
                teacher.setTeacherPhotoAddress(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int n = teacherService.updateTeacher(teacher);
        LoginUser loginUser=new LoginUser();
        loginUser.setLoginUserID(Integer.parseInt(request.getParameter("loginUserID")));
        loginUser.setLoginName(request.getParameter("loginName"));
        loginUser.setLoginPassword(request.getParameter("loginPassword"));
        int a = loginUserService.updateLoginUser(loginUser);
        if (n > 0&& a>0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }
    @ResponseBody
    @RequestMapping(value = "/AddProduct",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    public String addTeacher(Teacher teacher,String loginName,String loginPassword,
                             HttpSession session,
                             HttpServletRequest request,
                             @RequestParam(value = "teacherPhotoAdd",required = false)MultipartFile attach) {
        String filePath = null;
        //判断文件是否为空
        if(null!=attach && !attach.isEmpty()){
            String path = request.getSession().getServletContext().getRealPath("statics/upload");
            String oldFileName = attach.getOriginalFilename();//原文件名
            String prefix = FilenameUtils.getExtension(oldFileName);
            //判断文件大小。。todo
            //判断文件格式。。todo
            String fileName = System.currentTimeMillis()+ RandomUtils.nextInt(1000000)+"_product.jpg";
            File targetFile = new File(path, fileName);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            try {
                attach.transferTo(targetFile);
                teacher.setTeacherPhotoAddress(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int a = teacherService.addTeacher(teacher,loginName,loginPassword);
        if ( a>0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }
    @RequestMapping(value = "queryPhoto",method = RequestMethod.GET)
    public List<String> teacherPhoto() {
        List<String> list= teacherService.queryPhoto();
        return list;
    }


}
