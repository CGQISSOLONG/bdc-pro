package com.bdc.entity;

import lombok.Data;

import java.util.Date;

@Data
public class HjExceptionLog {

    //序号
    private Integer xh;

    //存储过程名
    private String proName;

    //行政区
    private String qxdm;

    //错误原因
    private String reason;

    //插入时间
    private Date crsj;
}
