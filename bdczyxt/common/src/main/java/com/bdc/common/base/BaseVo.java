package com.bdc.common.base;

import lombok.Data;

/**
 * @author 创建人：laill
 * @version 版本号：V1.0
 * <p>
 * **************************修订记录***************************************
 * <p>
 * 2019年05月27日 laill 创建该类功能。
 * <p>
 * ***********************************************************************
 * </p>
 * @ClassName 类名：BaseVo
 * @Description 功能说明：基础vo
 * <p>
 * TODO
 * </p>
 * ***********************************************************************
 * @date 创建日期：2019年05月27日
 */
@Data
public class BaseVo {
    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * 总数量
     */
    private long total;

    /**
     * 总页数
     */
    private int pages;

}
