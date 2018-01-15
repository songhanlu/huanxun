package com.bdqn.huanxun.pojo;

import java.net.PortUnreachableException;
import java.util.Date;

/**
 * Created by wangheng on 2018/1/9.
 */
public class Book {
    private Integer bookID;     //教材Id号
    private String bookTitle;   //教材的标题名称
    private String bookAddress;   //教材的上传地址
    private Date uploadTime;       //教材的上传时间
    private String bookVersion;
    private CourseType courseType;  //教材表里面嵌套一个课程类型courseTypeId,作为外键
    //教材版本
    private Integer visible;//工具列

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAddress='" + bookAddress + '\'' +
                ", uploadTime=" + uploadTime +
                ", bookVersion='" + bookVersion + '\'' +
                ", courseType=" + courseType +
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

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getBookVersion() {
        return bookVersion;
    }

    public void setBookVersion(String bookVersion) {
        this.bookVersion = bookVersion;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }


}
