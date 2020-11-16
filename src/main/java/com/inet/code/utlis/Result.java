package com.inet.code.utlis;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 返回值模式
 * @author HCY
 * @since 2020-10-29
 */
public class Result {
    /**
     * 时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timestamp;
    /**
     * 状态信息
     * 200 - 成功
     * 401 - 非法的
     * 403 - 禁止
     * 404 - 未找到
     * 500 - 错误
     */
    private Integer status;

    public static final Integer STATUS_OK_200 = 200;
    public static final Integer STATUS_ILLEGAL_401 = 401;
    public static final Integer STATUS_BAN_403 = 403;
    public static final Integer STATUS_NOT_FOUND_404 = 404;
    public static final Integer STATUS_ERROR_500 = 500;

    /**
     * 信息
     */
    private String info;

    public static final String INFO_OK_200 = "OK";
    public static final String INFO_ILLEGAL_401 = "ILLEGAL";
    public static final String INFO_BAN_403 = "BAN";
    public static final String INFO_NOT_FOUND_404 = "NOT FOUNT";
    public static final String INFO_ERROR_500 = "ERROR";
    /**
     * 详情
     */
    private String details;

    public static final String DETAILS_OK_200 = "成功";
    public static final String DETAILS_ILLEGAL_401 = "非法的";
    public static final String DETAILS_BAN_403 = "禁止的";
    public static final String DETAILS_NOT_FOUND_404 = "未找到";
    public static final String DETAILS_ERROR_500 = "错误";

    /**
     * 返回信息
     */
    private Object message;
    /**
     * 调用URL
     */
    private String path;

    /**
     * 空参
     */
    public Result() {
    }

    /**
     * 全参
     * @author HCY
     * @since 2020-10-29
     * @param status
     * @param info
     * @param details
     * @param message
     * @param path
     */
    public Result( Integer status, String info, String details, Object message, String path) {
        this.timestamp = new Date();
        this.status = status;
        this.info = info;
        this.details = details;
        this.message = message;
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp() {
        this.timestamp = new Date();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String error) {
        this.info = error;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Result{" +
                "timestamp=" + timestamp +
                ", status=" + status +
                ", info='" + info + '\'' +
                ", details='" + details + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
