package com.bdc.dao.dto;
import lombok.Data;

import java.util.Date;

@Data
public class HjfxMx {
    //序号
    private int xuhao;
    //行政区
    private String qxdm;
    // 业务号
    private String ywh;
    // 报文ID
    private String bwId;
    // 登记大类
    private String djdl;
    // 受理时间
    private Date slsj;
    // 登簿时间
    private Date dbsj;
    // 是否计入业务量/s是否计算办理时长
    private String sfjsblsc;
    //办理用时
    private double blys;
    // 说明
    private String remark;
    // 报文接收时间
    private Date bwjssj;
    //插入时间
    private Date crsj;
    //更新时间
    private Date gxsj;
    //是否外网办件
    private int sfwwbj;
}
