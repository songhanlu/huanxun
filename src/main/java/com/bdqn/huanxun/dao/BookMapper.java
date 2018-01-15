package com.bdqn.huanxun.dao;

import com.bdqn.huanxun.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangheng on 2018/1/9.
 */
public interface BookMapper {
    //分页.查询多条
    public List<Book> queryAll();
    //模糊查询教材名称
    public List<Book> queryBookTitle(@Param("bookTitle") String bookTitle,
                                     @Param("courseTypeName") String courseTypeName );
    //详情
    public Book queryBookById(Integer id);
    //删除
    public Integer deleteBookById(Integer id);
    //批量删除
    public Integer deleteBookByIdList(List<Integer> list);
}
