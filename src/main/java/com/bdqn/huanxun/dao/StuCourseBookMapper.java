package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.Book;
import com.bdqn.huanxun.pojo.StuCourseBook;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hp on 2018/1/17.
 */
public interface StuCourseBookMapper {
    //上传教材
    Integer addStuCourseBook(StuCourseBook stuCourseBook);

    //根据学生课程ID查询对应的教材列表
    List<StuCourseBook> findStuCourseBooks(Integer stuCourseID);

    //根据学生课程ID查询对应的教材列表（为下拉菜单用）
    List<Book> findBooksByStuCourseID(Integer courseTypeID);

    //根据教材类型ID和教材在其类型中的编号选出教材
    Book findBookByCourseTypeIDAndBookNoInType(@Param("courseTypeID") Integer couseTypeID,
                                               @Param("bnhy") Integer bookNoInHisType);

    //批量删除上传教材
    Integer deleteUploadBookByIDs(List<Integer> IDs);
}
