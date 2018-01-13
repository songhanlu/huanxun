package com.bdqn.huanxun.service;

import com.bdqn.huanxun.dao.ClassBookMapper;
import com.bdqn.huanxun.pojo.Book;
import com.bdqn.huanxun.pojo.ClassBook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2018/1/12.
 */
@Service
public class ClassBookServiceImpl implements ClassBookService {
    @Resource
    private ClassBookMapper classBookMapper;

    @Override
    public List<Book> findBooksByArrangeID(Integer stuClassArrangeID) {
        return classBookMapper.findBooksByArrangeID(stuClassArrangeID);
    }

    @Override
    public Integer addClassBook(ClassBook classBook) {
        return classBookMapper.addClassBook(classBook);
    }

    @Override
    public List<Book> findBooksByCourseTypeID(Integer courseTypeID) {
        return classBookMapper.findBooksByCourseTypeID(courseTypeID);
    }

    @Override
    public Integer deleteClassBook(Integer stuClassArrangeID) {
        return classBookMapper.deleteClassBook(stuClassArrangeID);
    }
}
