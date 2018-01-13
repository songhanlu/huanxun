package com.bdqn.huanxun.pojo;

/**
 * Created by hp on 2018/1/7.
 */
public class LessonType {
    private Integer lessonTypeID;
    private Integer timePerLesson;
    private String lessonArea;
    private Integer lessonPrice;
    private Integer isTry;
    private Integer visible;
    private String lessonDesc;

    public String getLessonDesc() {
        return lessonDesc;
    }

    public void setLessonDesc(String lessonDesc) {
        this.lessonDesc = lessonDesc;
    }

    public Integer getLessonTypeID() {
        return lessonTypeID;
    }

    public void setLessonTypeID(Integer lessonTypeID) {
        this.lessonTypeID = lessonTypeID;
    }

    public Integer getTimePerLesson() {
        return timePerLesson;
    }

    public void setTimePerLesson(Integer timePerLesson) {
        this.timePerLesson = timePerLesson;
    }

    public String getLessonArea() {
        return lessonArea;
    }

    public void setLessonArea(String lessonArea) {
        this.lessonArea = lessonArea;
    }

    public Integer getLessonPrice() {
        return lessonPrice;
    }

    public void setLessonPrice(Integer lessonPrice) {
        this.lessonPrice = lessonPrice;
    }

    public Integer getIsTry() {
        return isTry;
    }

    public void setIsTry(Integer isTry) {
        this.isTry = isTry;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return "LessonType{" +
                "lessonTypeID=" + lessonTypeID +
                ", timePerLesson=" + timePerLesson +
                ", lessonArea='" + lessonArea + '\'' +
                ", lessonPrice=" + lessonPrice +
                ", isTry=" + isTry +
                ", visible=" + visible +
                ", lessonDesc='" + lessonDesc + '\'' +
                '}';
    }
}
