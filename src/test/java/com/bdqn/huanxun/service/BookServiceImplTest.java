package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.BookMapper;
import com.bdqn.huanxun.pojo.Book;
import com.bdqn.huanxun.pojo.CourseType;
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
    @Test
    public void addBook() throws Exception {
        Book book=new Book();
        book.setBookTitle("新东方20");
        book.setBookAddress("测试sql语句");
        CourseType courseType=new CourseType();
        courseType.setCourseTypeID(1);
        book.setCourseType(courseType);
        book.setBookVersion("v1.0");
        int n=bookService.addBook(book);
        System.out.println(n);



    }

    @Test
    public void updateBook() throws Exception {
        Book book=new Book();
        book.setBookTitle("新东方英语1.0");
        CourseType courseType=new CourseType();
        //courseType.setCourseTypeName("新东方英语");
        courseType.setCourseTypeID(9); //修改它的id就是修改它的名称，通过idh获取教材类型
        book.setCourseType(courseType);
        book.setBookVersion("v2.0");
        book.setBookID(10);
        int n=bookService.updateBook(book);
        System.out.println(n);

    }
 //   @Test
//    public void queryBookTitle() throws Exception {
//        List<Book> list=bookService.queryBookTitle("剑","剑");
//        for (Book book : list) {
//            System.out.println(book);
//        }
//    }


    @Test
    public void queryBookById() throws Exception {
        Book book=bookService.queryBookById(7);
        System.out.println(book);
    }
//
//    @Test
//    public void deleteBookByIdList() throws Exception {
//        List<Integer> list=new ArrayList<Integer>();
//        list.add(1);
//        list.add(2);
//        Integer rows=bookService.deleteBookByIdList(list);
//        System.out.println(rows);
//    }

//    @Test
//    public void deleteBookById() throws Exception {
//        Integer rows=bookService.deleteBookById(8);
//        System.out.println(rows);
//    }



    @Resource
    private BookService bookService;
//    @Test
//    //分页测试
//    public void queryAll() throws Exception {
//        PageInfo<Book> pageInfo=bookService.queryAll(1,5);
//        List<Book> list=pageInfo.getList();
//        for (Book book : list) {
//            System.out.println(book);
//        }
//    }

}