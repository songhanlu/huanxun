package com.bdqn.huanxun.service;

import com.bdqn.huanxun.pojo.Book;
import com.bdqn.huanxun.pojo.StuCourseBook;
import com.bdqn.huanxun.pojo.StudentCourse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2018/1/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class StuCourseBookServiceImplTest {
    @Test
    public void addBooks() throws Exception {
        Integer stuCourseID = 30;
        Integer courseTypeID = 9;
        Integer startBook = 2;
        Integer endBook = 8;
        boolean result = stuCourseBookService.addBooks(stuCourseID,
                                                        courseTypeID,
                                                        startBook,
                                                        endBook);
        System.out.println(result);
    }

    @Resource
    private StuCourseBookService stuCourseBookService;


    @Test
    public void addStuCourseBook() throws Exception {
        StuCourseBook stuCourseBook = new StuCourseBook();
        Book book = new Book();
        book.setBookID(9);
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setStuCourseID(30);
        stuCourseBook.setBook(book);
        stuCourseBook.setStudentCourse(studentCourse);
        Integer result = stuCourseBookService.addStuCourseBook(stuCourseBook);
        System.out.println(result);

    }

    @Test
    public void findStuCourseBooks() throws Exception {
        List<StuCourseBook> stuCourseBooks = stuCourseBookService.findStuCourseBooks(30);
        System.out.println(stuCourseBooks);
    }



}