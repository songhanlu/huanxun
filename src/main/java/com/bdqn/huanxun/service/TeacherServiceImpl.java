package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.LoginUserMapper;
import com.bdqn.huanxun.dao.PartTeacherTimeMapper;
import com.bdqn.huanxun.dao.TeacherMapper;
import com.bdqn.huanxun.pojo.LoginUser;
import com.bdqn.huanxun.pojo.Teacher;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private PartTeacherTimeMapper partTeacherTimeMapper;
    @Resource
    private LoginUserMapper loginUserMapper;


    @Override
    public PageInfo<Teacher> queryAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> list=teacherMapper.queryAllTeacher();
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(list);
        return  new PageInfo<Teacher>(list);
    }
    @Override
    public Teacher queryTeacherById(Integer teacherID) {
        return teacherMapper.queryTeacherById(teacherID);
    }

    @Override
    public Integer deleteTeacherById(Integer id) {
        Teacher teacher=teacherMapper.queryTeacherById(id);
        loginUserMapper.deleteLoginUser_cf(teacher.getLoginUser().getLoginUserID());
       /* partTeacherTimeMapper.deleteTeacherTime(teacher.getPartTeacherTime().getPartTeacherTimeID());*/
        return teacherMapper.deleteTeacherById(id);
    }
    @Override
    public Integer deleteTeacherList(List<Integer> teacherIDs) {

        return teacherMapper.deleteTeacherList(teacherIDs);
    }

    @Override
    public List<Teacher> queryTeacherAll() {
        return teacherMapper.queryTeacherAll();
    }

    @Override
    public Integer updateTeacher(Teacher teacher) {
        loginUserMapper.updateLoginUser_cf(teacher.getLoginUser());
        return teacherMapper.updateTeacher(teacher);
    }

    @Override
    public Integer addTeacher(Teacher teacher,String loginName,String loginPassword) {
        LoginUser loginUser = new LoginUser();
        loginUser.setLoginName(loginName);
        loginUser.setLoginPassword(loginPassword);
        teacher.setLoginUser(loginUser);
        loginUserMapper.addLoginUser_cf(loginUser);
        return teacherMapper.addTeacher(teacher);
    }

    @Override
    public PageInfo<Teacher> queryAllByNameAndGender(Integer pageNum, Integer pageSize, String teacherName, String loginName) {
        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> list = teacherMapper.queryTeacherByUserNameAndGender(teacherName, loginName);
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(list);
        return new PageInfo<Teacher>(list);
    }

    @Override
    public List<String> queryPhoto() {
        return teacherMapper.queryPhoto();
    }

    @Override
    public List<Teacher> findTeachersByCareerType(String careerType) {
        return teacherMapper.findTeachersByCareerType(careerType);
    }


}
