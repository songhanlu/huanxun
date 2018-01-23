package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.Book;
import com.bdqn.huanxun.pojo.StuCourseBook;
import com.bdqn.huanxun.tools.PageUtil;

import java.util.List;

/**
 * Created by hp on 2018/1/17.
 */
public interface StuCourseBookService {
    //单个上传教材
    Integer addStuCourseBook(StuCourseBook stuCourseBook);

    //根据学生课程ID查询对应的教材列表
    List<StuCourseBook> findStuCourseBooks(Integer stuCourseID);

    //分页显示学生课程ID查询对应的教材列表
    PageUtil<StuCourseBook> findStuCourseBooks(Integer stuCourseID,
                                               Integer pageNum,
                                               Integer pageSize);

    //根据学生课程ID查询对应的教材列表
    @Deprecated
    List<Book> findStuCourseBooks(Integer stuCourseID, String getBook);

    List<Book> findBooksByStuCourseID(Integer courseTypeID);

    //批量上传教材
    /**
     *
     * @param stuCourseID 学生课程
     * @param courseTypeID  教材类型
     * @param startBook  开始教材
     * @param endBook  结束教材
     * @return  成功返回true，失败返回false
     */
    Boolean addBooks(Integer stuCourseID,
                     Integer courseTypeID,
                     Integer startBook,
                     Integer endBook);

    //批量删除上传教材
    Integer deleteUploadBookByIDs(List<Integer> IDs);
}
