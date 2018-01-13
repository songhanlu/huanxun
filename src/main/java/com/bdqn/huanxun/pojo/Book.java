package com.bdqn.huanxun.pojo;

import java.util.Date;

/**
 * Created by hp on 2018/1/12.
 */
public class Book {
    private Integer bookID;
    private String bookTitle;
    private String bookAddress;
    private Integer courseTypeID;
    private Date uplodTime;
    private String bookVersion;
    private Integer visible;

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAddress='" + bookAddress + '\'' +
                ", courseTypeID=" + courseTypeID +
                ", uplodTime=" + uplodTime +
                ", bookVersion='" + bookVersion + '\'' +
                ", visible=" + visible +
                '}';
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAddress() {
        return bookAddress;
    }

    public void setBookAddress(String bookAddress) {
        this.bookAddress = bookAddress;
    }

    public Integer getCourseTypeID() {
        return courseTypeID;
    }

    public void setCourseTypeID(Integer courseTypeID) {
        this.courseTypeID = courseTypeID;
    }

    public Date getUplodTime() {
        return uplodTime;
    }

    public void setUplodTime(Date uplodTime) {
        this.uplodTime = uplodTime;
    }

    public String getBookVersion() {
        return bookVersion;
    }

    public void setBookVersion(String bookVersion) {
        this.bookVersion = bookVersion;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }
}
