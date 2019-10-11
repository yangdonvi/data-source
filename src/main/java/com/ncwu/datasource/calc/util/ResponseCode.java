package com.ncwu.datasource.calc.util;

/**
 * auth ydw
 * ResponseCode
 */
public enum ResponseCode {
    //成功
    SUCCESS("200","SUCCESS"),
    //错误
    ERROR("0","ERROR"),
    //异常
    EXCEPTION("2","EXCEPTION");

    /**
     * 状态码
     */
    private final String code;
    /**
     * 描述信息
     */
    private final String desc;

    ResponseCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }
}
