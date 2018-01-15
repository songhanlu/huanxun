package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.Book;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangheng on 2018/1/9.
 */
public interface BookService {
    //分页
    PageInfo<Book> queryAll(Integer pageNum,Integer pageSize);
    //模糊查询教材名称
   // public List<Book> queryBookTitle(Integer pageNum,Integer pageSize,String bookTitle, String courseTypeName );
    public PageInfo<Book> queryBookTitle(Integer pageNum,Integer pageSize,String bookTitle, String courseTypeName );
    //单条删除
    public Integer deleteBookById(Integer id);
    //批量删除
    public Integer deleteBookByIdList(List<Integer> list);
    //详情
    public Book queryBookById(Integer id);



}
