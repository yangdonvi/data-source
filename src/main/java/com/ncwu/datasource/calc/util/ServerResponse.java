package com.ncwu.datasource.calc.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 保障序列化json的时候，如果是null对象，key也会消失
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {
    private static final long serialVersionUID = -7743624840999524733L;
    /**
     * 状态
     */
    private String code;
    /**
     * 描述信息
     */
    @SerializedName(value = "msg", alternate = {"message"})
    private String msg;
    /**
     * 数据
     */
    private T data;


    public ServerResponse() {
    }

    private ServerResponse(String code) {
        this.code = code;
    }
    private ServerResponse(String code, T data) {
        this.code = code;
        this.data = data;
    }
    private ServerResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    private ServerResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 使之不在json序列化结果中
     */
    @JsonIgnore
    public boolean isSuccess() {
        return this.code == ResponseCode.SUCCESS.getCode();
    }

    public String getCode() {
        return code;
    }
    public T getData() {
        return data;
    }
    public String getMsg() {
        return msg;
    }

    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc());
    }
    public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }
    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(),data);
    }

    public static <T> ServerResponse<T> createByError() {
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }
    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }
    public static <T> ServerResponse<T> createByErrorCodeMessage(String errorCode,String errorMessage) {
        return new ServerResponse<T>(errorCode,errorMessage);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setCode(String code) {

        this.code = code;
    }
}
