package com.ncwu.datasource.calc.entity;

/**
 * Created by Donvi Yang on 2019/4/29.
 */
public class Attendance {
    private String deptName;
    private String majorName;
    private String stuName;
    private Integer sortNum;
    private Integer totalNum;
    private String beginDate;
    private String endDate;

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
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

    public String getDeptName() {

        return deptName;
    }

    public String getMajorName() {
        return majorName;
    }

    public String getStuName() {
        return stuName;
    }

    public Integer getSortNum() {
        return sortNum;
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
