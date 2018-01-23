package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.StuCourseBookMapper;
import com.bdqn.huanxun.pojo.Book;
import com.bdqn.huanxun.pojo.StuCourseBook;
import com.bdqn.huanxun.pojo.StudentCourse;
import com.bdqn.huanxun.tools.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2018/1/17.
 */
@Service
public class StuCourseBookServiceImpl implements StuCourseBookService {
    @Resource
    private StuCourseBookMapper stuCourseBookMapper;

    @Override
    public Integer addStuCourseBook(StuCourseBook stuCourseBook) {
        return stuCourseBookMapper.addStuCourseBook(stuCourseBook);
    }

    @Override
    public List<StuCourseBook> findStuCourseBooks(Integer stuCourseID) {
        return stuCourseBookMapper.findStuCourseBooks(stuCourseID);
    }

    @Override
    public PageUtil<StuCourseBook> findStuCourseBooks(Integer stuCourseID, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<StuCourseBook> stuCourseBooks = stuCourseBookMapper.findStuCourseBooks(stuCourseID);
        return new PageUtil<>(new PageInfo<StuCourseBook>(stuCourseBooks));
    }

    @Override
    public List<Book> findStuCourseBooks(Integer stuCourseID, String getBook) {
        List<StuCourseBook> stuCourseBooks = stuCourseBookMapper.findStuCourseBooks(stuCourseID);
        List<Book> books = new ArrayList<>();
        if(stuCourseBooks!=null){
            for (StuCourseBook stuCourseBook : stuCourseBooks) {
                books.add(stuCourseBook.getBook());
            }
        }
        return books;
    }

    @Override
    public List<Book> findBooksByStuCourseID(Integer courseTypeID) {
        return stuCourseBookMapper.findBooksByStuCourseID(courseTypeID);
    }

    @Override
    public Boolean addBooks(Integer stuCourseID, Integer courseTypeID, Integer startBook, Integer endBook) {
        boolean result = true;
        for(int i = startBook; i <=endBook; i ++){
            StudentCourse studentCourse = new StudentCourse();
            studentCourse.setStuCourseID(stuCourseID);

            Book book = stuCourseBookMapper.findBookByCourseTypeIDAndBookNoInType(courseTypeID, i);

            StuCourseBook stuCourseBook = new StuCourseBook();
            stuCourseBook.setStudentCourse(studentCourse);
            stuCourseBook.setBook(book);
            int r = stuCourseBookMapper.addStuCourseBook(stuCourseBook);
            if(r<1){
                result = false;
                throw new RuntimeException("添加教材失败");
            }
        }
        return result;
    }

    @Override
    public Integer deleteUploadBookByIDs(List<Integer> IDs) {
        return stuCourseBookMapper.deleteUploadBookByIDs(IDs);
    }
}
