package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.BookMapper;
import com.bdqn.huanxun.pojo.Book;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangheng on 2018/1/9.
 */
@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;
    @Override
    public PageInfo<Book> queryAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Book> list=bookMapper.queryAll();
        PageInfo<Book> pageInfo=new PageInfo<Book>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Book> queryBookTitle(Integer pageNum, Integer pageSize, String bookTitle, String courseTypeName) {
        PageHelper.startPage(pageNum,pageSize);
        List<Book> list=bookMapper.queryBookTitle(bookTitle, courseTypeName);
        PageInfo<Book> pageInfo=new PageInfo<Book>(list);
        return pageInfo;
    }


    @Override
    public Integer deleteBookById(Integer id) {
        return bookMapper.deleteBookById(id);
    }

    @Override
    public Integer deleteBookByIdList(List<Integer> list) {
        return bookMapper.deleteBookByIdList(list);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookMapper.queryBookById(id);
    }
}
