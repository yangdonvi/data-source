package com.ncwu.datasource.calc.entity;

/**
 * Created by Donvi Yang on 2019/4/29.
 */
public class BaseData {
    private Integer totalOutBookNum;
    private String languageType;
    private Integer languageNum;
    private String campusName;
    private Integer CampusOutBookNum;
    private Double avgLendNum;
    private String beginDate;
    private String endDate;

    public void setTotalOutBookNum(Integer totalOutBookNum) {
        this.totalOutBookNum = totalOutBookNum;
    }



    public void setAvgLendNum(Double avgLendNum) {
        this.avgLendNum = avgLendNum;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getTotalOutBookNum() {

        return totalOutBookNum;
    }

    public void setLanguageType(String languageType) {
        this.languageType = languageType;
    }

    public void setLanguageNum(Integer languageNum) {
        this.languageNum = languageNum;
    }

    public String getLanguageType() {

        return languageType;
    }

    public Integer getLanguageNum() {
        return languageNum;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public void setCampusOutBookNum(Integer campusOutBookNum) {
        CampusOutBookNum = campusOutBookNum;
    }

    public String getCampusName() {

        return campusName;
    }

    public Integer getCampusOutBookNum() {
        return CampusOutBookNum;
    }

    public Double getAvgLendNum() {
        return avgLendNum;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
