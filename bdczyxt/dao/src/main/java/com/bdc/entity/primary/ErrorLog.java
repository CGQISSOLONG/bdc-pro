package com.bdc.entity.primary;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;



@Data
public class ErrorLog {


    private Integer id;

    private String ip;

    private String account;

    /**
     * 类名
     */
    private String className;

    /**
     * 请求接口路径
     */
    private String interfaceUrl;

    /**
     * 请求种类
     */
    private String methods;

    /**
     * 执行参数
     */
    private String params;

    /**
     * 返回code
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 程序异常时间
     */

    private String exceptionTime;

    /**
     * 0代表是web系统 1-admin系统
     */
    private Integer type;
}
