package com.ncwu.datasource.entity;

import java.sql.Date;

/**
 * Created by Donvi Yang on 2019/3/24.
 */
public class BorrowRecordBuild {
    private Integer id;
    private String readerId;
    private String readerName;
    private String barCode;
    private String bookName;
    private Date lentTime;
    private Date limitReturnTime;
    private Integer renewTimes;
    private String circulateType;
    private String bookType;
    private String bookAddress;
    private String actor;
    private String returnActor;
    private String version;
    private String press;
    private String publicTime;
    private String pages;
    private Integer sex;
    private String department;
    private String major;
    private Integer grade;
    private String findNumber;
    private String author;

    public void setFindNumber(String findNumber) {
        this.findNumber = findNumber;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFindNumber() {

        return findNumber;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getSex() {

        return sex;
    }

    public String getDepartment() {
        return department;
    }

    public String getMajor() {
        return major;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setLentTime(Date lentTime) {
        this.lentTime = lentTime;
    }

    public void setLimitReturnTime(Date limitReturnTime) {
        this.limitReturnTime = limitReturnTime;
    }

    public void setRenewTimes(Integer renewTimes) {
        this.renewTimes = renewTimes;
    }

    public void setCirculateType(String circulateType) {
        this.circulateType = circulateType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public void setBookAddress(String bookAddress) {
        this.bookAddress = bookAddress;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public void setReturnActor(String returnActor) {
        this.returnActor = returnActor;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public void setPublicTime(String publicTime) {
        this.publicTime = publicTime;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setLanguageType(String languageType) {
        this.languageType = languageType;
    }

    public Integer getId() {

        return id;
    }

    public String getReaderId() {
        return readerId;
    }

    public String getReaderName() {
        return readerName;
    }

    public String getBarCode() {
        return barCode;
    }

    public String getBookName() {
        return bookName;
    }

    public Date getLentTime() {
        return lentTime;
    }

    public Date getLimitReturnTime() {
        return limitReturnTime;
    }

    public Integer getRenewTimes() {
        return renewTimes;
    }

    public String getCirculateType() {
        return circulateType;
    }

    public String getBookType() {
        return bookType;
    }

    public String getBookAddress() {
        return bookAddress;
    }

    public String getActor() {
        return actor;
    }

    public String getReturnActor() {
        return returnActor;
    }

    public String getVersion() {
        return version;
    }

    public String getPress() {
        return press;
    }

    public String getPublicTime() {
        return publicTime;
    }

    public String getPages() {
        return pages;
    }

    public Double getPrice() {
        return price;
    }

    public String getLanguageType() {
        return languageType;
    }

    private Double price;
    private String languageType;

    public String toBeanColumn() {
        return "insert into BORROW_RECORD_BUILD (READER_ID,READER_NAME,BAR_CODE,BOOK_NAME," +
                "LENT_TIME,LIMIT_RETURN_TIME,RENEW_TIMES,CIRCULATE_TYPE,BOOK_TYPE," +
                "BOOK_ADDRESS,ACTOR,RETURN_ACTOR,FIND_NUMBER,AUTHOR,VERSION,PRESS," +
                "PUBLIC_TIME,PAGES,PRICE,LANGUAGE_TYPE,SEX,DEPARTMENT,MAJOR,GRADE) VALUES ";
    }

    @Override()
    public String toString() {
        return "('"+readerId+"','"+readerName+"','"+barCode+"','"+bookName+"','"+lentTime+"','"+limitReturnTime+"',"+renewTimes+",'"+circulateType+"','"+bookType+"','"+bookAddress+"','"+
                actor+"','"+returnActor+"','"+findNumber+"','"+author+"','"+version+"','"+press+"','"+publicTime+"','"+pages+"',"+price+",'"+languageType+"',"+sex+",'"+department+"','"+major+"',"+grade+") ";
    }


}
