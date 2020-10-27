package com.bdc.dao.req;

import lombok.Data;

/**
 * @ClassName 类名：BjfxReq
 * @Description 功能说明：办件分析
 */
@Data
public class BjfxReq {
    /**
     * 登记类型
     */
    private String djdl;

    /**
     * 开始日期
     */
    private String startDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 时间段
     */
    private String sj;

    /**
     * 区县代码
     */
    private String qxdm;

    /**
     * 查询类别
     */
    private String zl;

    /**
     * 区分地市
     */
    private String qxdmTemp;

    /**
     * 统计周期
     */
    private String period;

}
