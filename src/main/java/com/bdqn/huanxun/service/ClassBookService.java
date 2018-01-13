package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.Book;
import com.bdqn.huanxun.pojo.ClassBook;

import java.util.List;

/**
 * Created by hp on 2018/1/12.
 */
public interface ClassBookService {
    //根据arrangeID查询list
    List<Book> findBooksByArrangeID(Integer stuClassArrangeID);

    //增加ClassBook记录
    Integer addClassBook(ClassBook classBook);

    //根据courseTypeID查询Book集合
    List<Book> findBooksByCourseTypeID(Integer courseTypeID);

    //根据arrangeID删除ClassBook记录
    Integer deleteClassBook(Integer stuClassArrangeID);
}
