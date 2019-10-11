package com.ncwu.datasource.calc.entity;

/**
 * Created by Donvi Yang on 2019/5/1.
 */
public class ReaderNum {

    // 单位名称
    private String departName;
    // 学院人数
    private Integer departmentNum;
    // 专业人数
    private Integer majorNum;
    // 职工人数
    private Integer workerNum;
    // 全校人数
    private Integer schoolNum;

    public void setSchoolNum(Integer schoolNum) {
        this.schoolNum = schoolNum;
    }

    public Integer getSchoolNum() {

        return schoolNum;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public void setDepartmentNum(Integer departmentNum) {
        this.departmentNum = departmentNum;
    }

    public void setMajorNum(Integer majorNum) {
        this.majorNum = majorNum;
    }

    public void setWorkerNum(Integer workerNum) {
        this.workerNum = workerNum;
    }

    public String getDepartName() {

        return departName;
    }

    public Integer getDepartmentNum() {
        return departmentNum;
    }

    public Integer getMajorNum() {
        return majorNum;
    }

    public Integer getWorkerNum() {
        return workerNum;
    }
}
