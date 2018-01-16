package com.bdqn.huanxun.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.huanxun.pojo.Book;
import com.bdqn.huanxun.pojo.CourseType;
import com.bdqn.huanxun.service.BookService;
import com.bdqn.huanxun.service.CourseTypeService;
import com.bdqn.huanxun.tools.Message;
import com.bdqn.huanxun.tools.PageUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.lang.model.type.ErrorType;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
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
    @Resource
    private CourseTypeService courseTypeService;

    //跳页面
    @RequestMapping(value = "/tobook.do")
    public String tobook(){
        return "book/book";
    }
//    //分页
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
    //修改
    @ResponseBody
    @RequestMapping(value = "/updateBook.do",method = RequestMethod.POST,
            produces = {"application/json;charset=utf-8"})
    public String updateBook(Book book){
        Integer rows=bookService.updateBook(book);
        if (rows>0){
            return JSON.toJSONString(Message.success());
        }else {
            return JSON.toJSONString(Message.error());
        }
    }
    @ResponseBody
    @RequestMapping(value = "/addBook.do",method = RequestMethod.POST,
            produces = {"application/json;charset=utf-8"})
    public String addBook(Book book, HttpServletRequest request,
                          @RequestParam(value = "bookAddress_file",required = false)MultipartFile[] attach){
        //上传文件
        String filePath = null;
        //判断文件是否为空
        System.out.println(attach[0].isEmpty());
        if(null!=attach && !attach[0].isEmpty()){
            String path = request.getSession().getServletContext().getRealPath("statics/upload");
            String oldFileName = attach[0].getOriginalFilename();//原文件名
            String prefix = FilenameUtils.getExtension(oldFileName);//文件名后缀
            //判断文件大小。。todo
            //判断文件格式。。todo
            String fileName = System.currentTimeMillis()+ RandomUtils.nextInt(1000000)+"_product.pdf";//定义文件名上传格式
            File targetFile = new File(path, fileName);//传文件路径和文件名
            if(!targetFile.exists()){      //判断文件时否存在
                targetFile.mkdirs();
            }
            try {
                attach[0].transferTo(targetFile);
                book.setBookAddress(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                //提示文件上传失败。。todo

            }

        }

        int n = bookService.addBook(book);
        if (n > 0) {
            return JSON.toJSONString(Message.success());
        }
        return JSON.toJSONString(Message.error());
    }
    //查询教材类型给教材添加时的添加教材的下拉框使用
    @ResponseBody
    @RequestMapping(value = "queryCourseTypeToBook",method = RequestMethod.GET,
            produces = {"application/json;charset=utf-8"})
    public String queryCourseTypeToBook(){
        List<CourseType> list=courseTypeService.queryCourseTypeToBook();
        return JSON.toJSONString(list);
    }



   /* //添加和上传文件
    @ResponseBody
    @RequestMapping(value = "/updateBook.do",method = RequestMethod.POST,
            produces = {"application/json;charset=utf-8"})
    public String addBook(Book book, HttpServletRequest request,
                          @RequestParam(value = "bookAddress",required = false)MultipartFile multipartFile){
       *//* String bookAddress=null;
        //判断文件是否为空
        if (!multipartFile.isEmpty()){
            //定义上传路径
            String path=request.getSession().getServletContext().getRealPath("statics"+ File.separator+"uploadfiles");
           //获取源文件名
            String  oldFilename=multipartFile.getOriginalFilename();//源文件名
            //源文件后缀
            String prefix= FilenameUtils.getExtension(oldFilename);
            //定义文件大小
            int fileSize=500000;
            if (multipartFile.getSize()>fileSize){
                request.setAttribute("uploadFileError","*上传大小不能超过500");//判断文件大小
                //return "book/book";//去首页
            }else if (prefix.equalsIgnoreCase("pdf")){
                //新的文件名 当前系统时间加随机数
                String fileName=System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"book.pdf";
                //创建一个新的file对象
                File targetFile=new File(path,fileName);//上传路径和文件名
                //判断文件是否存在
                if (!targetFile.exists()){
                    targetFile.mkdirs();
                }
                try {
                    multipartFile.transferTo(targetFile);
                    book.setBookAddress(bookAddress);
                } catch (Exception e) {
                    e.printStackTrace();
                    //如果上传失败
                    request.setAttribute("uploadFileError","*上传文件失败");
                    //return "book/book";
                }
                bookAddress=path+File.separator+fileName;

            }else {
                request.setAttribute("uploadFileError","*上传文件格式不正确");//判断文件大小
                //return "book/book";//去首页
            }

        }
        Integer n=bookService.addBook(book);
*//*
        return null;
    }*/
}
