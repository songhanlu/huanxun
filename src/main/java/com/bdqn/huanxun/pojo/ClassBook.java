package com.bdqn.huanxun.pojo;

/**
 * Created by hp on 2018/1/12.
 */
public class ClassBook {
    private Integer classBookID;
    private Integer stuClassArrangeID;
    private Book book;

    @Override
    public String toString() {
        return "ClassBook{" +
                "classBookID=" + classBookID +
                ", stuClassArrangeID=" + stuClassArrangeID +
                ", book=" + book +
                '}';
    }

    public Integer getClassBookID() {
        return classBookID;
    }

    public void setClassBookID(Integer classBookID) {
        this.classBookID = classBookID;
    }

    public Integer getStuClassArrangeID() {
        return stuClassArrangeID;
    }

    public void setStuClassArrangeID(Integer stuClassArrangeID) {
        this.stuClassArrangeID = stuClassArrangeID;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
