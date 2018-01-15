package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.BookMapper;
import com.bdqn.huanxun.pojo.Book;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wangheng on 2018/1/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BookServiceImplTest {
   /* @Test
    public void queryBookTitle() throws Exception {
        List<Book> list=bookService.queryBookTitle("剑","剑");
        for (Book book : list) {
            System.out.println(book);
        }
    }*/


    @Test
    public void queryBookById() throws Exception {
        Book book=bookService.queryBookById(7);
        System.out.println(book);
    }

    @Test
    public void deleteBookByIdList() throws Exception {
        List<Integer> list=new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        Integer rows=bookService.deleteBookByIdList(list);
        System.out.println(rows);
    }

    @Test
    public void deleteBookById() throws Exception {
        Integer rows=bookService.deleteBookById(8);
        System.out.println(rows);
    }



    @Resource
    private BookService bookService;
    @Test
    //分页测试
    public void queryAll() throws Exception {
        PageInfo<Book> pageInfo=bookService.queryAll(1,5);
        List<Book> list=pageInfo.getList();
        for (Book book : list) {
            System.out.println(book);
        }
    }

}