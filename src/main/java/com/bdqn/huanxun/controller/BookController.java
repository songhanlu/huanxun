package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.pojo.Book;
import com.bdqn.huanxun.service.BookService;
import com.bdqn.huanxun.tools.Message;
import com.bdqn.huanxun.tools.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangheng on 2018/1/9.
 */
@Controller
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookService bookService;

    //跳页面
    @RequestMapping(value = "/tobook.do")
    public String tobook(){
        return "book/book";
    }
    //分页
    @ResponseBody
    @RequestMapping(value = "/queryAll.do",method = RequestMethod.GET,
    produces = {"application/json;charset=utf-8"})
    private String queryAll(Integer page,Integer rows,String bookTitle,String courseTypeName){
        PageInfo<Book> pageInfo=bookService.queryBookTitle(page, rows, bookTitle, courseTypeName);
        PageUtil<Book> pageUtil=new PageUtil<Book>(pageInfo);
        return JSON.toJSONString(pageUtil);

    }
    //删除一条
    @ResponseBody
    @RequestMapping(value = "/deleteBookById.do",method = RequestMethod.POST,
    produces = {"application/json;charset=utf-8"})
    public String deleteBookById(Integer id){
        Integer rows=bookService.deleteBookById(id);
        if (rows>0){
            return JSON.toJSONString(Message.success());
        }else {
            return JSON.toJSONString(Message.error());
        }
    }
    @ResponseBody
    @RequestMapping(value = "/deleteBookByIdList.do",method = RequestMethod.POST,
            produces = {"application/json;charset=utf-8"})
    public String deleteBookByIdList(String id){
        String [] bookId=id.split(",");
/*
        System.out.println(bookId);
*/
        List<Integer> list=new ArrayList<Integer>();
        for (String s : bookId) {
            list.add(Integer.parseInt(s));
        }
        Integer rows=bookService.deleteBookByIdList(list);
        if (rows>0){
            return JSON.toJSONString(Message.success());
        }else {
            return JSON.toJSONString(Message.error());
        }

    }
    //详情
    @ResponseBody
    @RequestMapping(value = "/queryBookById.do",method = RequestMethod.GET,
    produces = {"application/json;charset=utf-8"})
    public String queryBookById(Integer id){
        Book book=bookService.queryBookById(id);
        return JSON.toJSONString(book);
    }
    /* //按模糊查询
     @ResponseBody
     @RequestMapping(value = "/queryBookTitle.do",method = RequestMethod.GET,
             produces = {"application/json;charset=utf-8"})
    public String queryBookTitle(String bookTitle,String courseTypeName){
        List<Book> list=bookService.queryBookTitle(bookTitle, courseTypeName);
        return JSON.toJSONString(list);
    }*/
}
