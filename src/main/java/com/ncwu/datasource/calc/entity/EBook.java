package com.ncwu.datasource.calc.entity;

/**
 * Created by Donvi Yang on 2019/5/1.
 */
public class EBook {

    private String libName;

    private Integer visitNum;

    private Integer downNum;

    public void setLibName(String libName) {
        this.libName = libName;
    }

    public void setVisitNum(Integer visitNum) {
        this.visitNum = visitNum;
    }

    public void setDownNum(Integer downNum) {
        this.downNum = downNum;
    }

    public String getLibName() {

        return libName;
    }

    public Integer getVisitNum() {
        return visitNum;
    }

    public Integer getDownNum() {
        return downNum;
    }
}
