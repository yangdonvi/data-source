package com.ncwu.datasource.calc.entity;

/**
 * Created by Donvi Yang on 2019/4/29.
 */
public class BookWorth {
    private String departName;
    private Integer sortNum;
    private String bookName;
    private Integer totalNum;
    private String beginDate;
    private String endDate;

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDepartName() {

        return departName;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public String getBookName() {
        return bookName;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
