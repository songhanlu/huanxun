package com.bdqn.huanxun.dao.data;

/**
 * Created by hp on 2018/1/11.
 */
public interface DataStuCourseMapper {
    //根据课程LessonTypeID查询课程数量
    Integer countStuCourseByLessonTypeID(Integer lessonTypeID);
}
