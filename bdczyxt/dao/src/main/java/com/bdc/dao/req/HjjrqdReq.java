package com.bdc.dao.req;

import lombok.Data;

/**
 * @ClassName 类名：BjfxReq
 * @Description 功能说明：办件分析
 */
@Data
public class HjjrqdReq {
    /**
     * 业务号
     */
    private String ywh;

    /**
     * 登记类型
     */
    private String djlx;

    /**
     * 权利类型
     */
    private String qllx;

    /**
     * 区县代码
     */
    private Integer qxdm;

    /**
     * 受理时间
     */
    private String slsj;

    /**
     * 统计分类
     */
    private String tjfl;

    /**
     * 起末时间
     */
    private String start;
    private String end;

}
