package com.bdc.dao.dto;

import lombok.Data;

/**
 * @author 创建人：laill
 * @version 版本号：V1.0
 * <p>
 * **************************修订记录***************************************
 * <p>
 * 2019年11月18日 laill 创建该类功能。
 * <p>
 * ***********************************************************************
 * </p>
 * @ClassName 类名：HjfxDto
 * @Description 功能说明：汇交分析
 * <p>
 * TODO
 * </p>
 * ***********************************************************************
 * @date 创建日期：2019年11月18日
 */
@Data
public class HjfxDto {
    private Integer qxdm;

    private String qxdmName;
    /**
     * 评价分
     */
    private String evaluationScore;
    /**
     * 原始分
     */
    private String originalValue;

    /**
     * 排名
     */
    private String rank;

    private String midUse;
}
