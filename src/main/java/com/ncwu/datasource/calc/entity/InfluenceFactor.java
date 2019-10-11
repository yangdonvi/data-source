package com.ncwu.datasource.calc.entity;

/**
 * Created by Donvi Yang on 2019/4/29.
 */
public class InfluenceFactor {
    private Integer month;
    private Integer totalNum;
    private Integer grade;
    private String bookName;
    private Integer sortNum;
    private String beginDate;
    private String endDate;

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBeginDate() {

        return beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Integer getMonth() {

        return month;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public Integer getGrade() {
        return grade;
    }

    public String getBookName() {
        return bookName;
    }

    public Integer getSortNum() {
        return sortNum;
    }
}
