package com.bdqn.huanxun.dao.data;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2018/1/11.
 */
public interface DataStuCourseMapper {
    //根据课程LessonTypeID查询课程数量
    Integer countStuCourseByLessonTypeID(Integer lessonTypeID);

    //根据年统计新报名数
    List<Map<String, Object>> findStuCourseByYear();
    //根据月查询新报名数
    List<Map<String, Object>> findStuCourseByMonth();
    //根据季度查询新报名数
    List<Map<String, Object>> findStuCourseByQuaterYear();
}
