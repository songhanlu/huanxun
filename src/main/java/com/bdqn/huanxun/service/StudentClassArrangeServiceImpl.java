package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.ClassBookMapper;
import com.bdqn.huanxun.dao.StudentClassArrangeMapper;
import com.bdqn.huanxun.dao.StudentCourseMapper;
import com.bdqn.huanxun.pojo.Book;
import com.bdqn.huanxun.pojo.CourseType;
import com.bdqn.huanxun.pojo.StudentClassArrange;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hp on 2018/1/9.
 */
@Service
public class StudentClassArrangeServiceImpl implements StudentClassArrangeService {
    @Resource
    private StudentClassArrangeMapper studentClassArrangeMapper;
    @Resource
    private ClassBookMapper classBookMapper;
    @Resource
    private StudentCourseMapper studentCourseMapper;

    @Override
    public PageInfo<StudentClassArrange> findstuClassArranges(Integer pageNum, Integer pageSize, Integer stuCourseID) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentClassArrange> studentClassArranges = studentClassArrangeMapper.findstuClassArranges(stuCourseID);
        return new PageInfo<>(studentClassArranges);
    }

    @Override
    public Integer addStuClassArrange(StudentClassArrange studentClassArrange) {
        return studentClassArrangeMapper.addStuClassArrange(studentClassArrange);
    }

    @Override
    public Integer countClassArrange(Integer stuCourseID) {
        return studentClassArrangeMapper.countClassArrange(stuCourseID);
    }

    @Override
    public Integer deleteClassArrange(String IDs) {
        List<Integer> dd = new ArrayList<>();
        String[] ids = IDs.split(",");
        for (String id : ids) {
            dd.add(Integer.parseInt(id));
        }
        int result = studentClassArrangeMapper.deleteClassArrange(dd);
        return result;
    }

    @Override
    public StudentClassArrange findStuClassArrangeByID(Integer id) {
        return studentClassArrangeMapper.findStuClassArrangeByID(id);
    }

    @Override
    public Integer updateArrange(StudentClassArrange studentClassArrange) {
        return studentClassArrangeMapper.updateArrange(studentClassArrange);
    }

    @Override
    public Boolean teacherTimeIsNotDupe(Integer times, Integer teacherID,
                                     Date startTimeInput, Date endTimeInput,Integer currentArrangeID) {
        boolean flag = true;
        List<StudentClassArrange> arranges = studentClassArrangeMapper.findStuClassArrangeByTeaherID(teacherID);
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        outter:for(int i = 0; i < times; i++){
            for (StudentClassArrange arrange : arranges) {
                if(currentArrangeID==arrange.getStuClassArrangeID()){
                    continue;
                }
                if(null==arrange){
                    continue;
                }else{
                    Date startTime = arrange.getStartTime();
                    Date endTime = arrange.getEndTime();
                    if(endTimeInput.getTime()<=startTime.getTime() || startTimeInput.getTime()>=endTime.getTime()){
                        continue;
                    }else{
                        flag = false;
                        break outter;
                    }
                }
            }

        }
        return flag;
    }

    @Override
    public List<Book> findBooksCanChooseByArrangeID(Integer arrangeID) {
        //第一步：根据arrangeID查出当前课表所属的stuCourseID
        Integer stuCourseID = studentClassArrangeMapper.findStuCourseIDByArrangeID(arrangeID);
        //第二步：根据stuCourseID查出CourseType
        CourseType courseType = studentCourseMapper.findCourseTypeByStuCourseID(stuCourseID);
        //第三步：根据courseTypeID查出可选择的教材列表
        List<Book> books = classBookMapper.findBooksByCourseTypeID(courseType.getCourseTypeID());
        return books;
    }

    @Override
    public Integer updateStuClassArrangeStatus(StudentClassArrange arrange) {
        return studentClassArrangeMapper.updateStuClassArrangeStatus(arrange);
    }
}
