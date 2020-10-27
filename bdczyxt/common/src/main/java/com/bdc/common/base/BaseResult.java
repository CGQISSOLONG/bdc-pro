package com.bdc.common.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 创建人：laill
 * @version 版本号：V1.0
 * <p>
 * **************************修订记录***************************************
 * <p>
 * 2019年05月13日 laill 创建该类功能。
 * <p>
 * ***********************************************************************
 * </p>
 * @ClassName 类名：BaseResult
 * @Description 功能说明：统一返回结构类型
 * <p>
 * TODO
 * </p>
 * ***********************************************************************
 * @date 创建日期：2019年05月13日
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = -6959952431964699958L;

    /**
     * 状态码：200成功，其他为失败
     */
    public Integer code;

    /**
     * 成功为success，其他为失败原因
     */
    public String msg;


    public Long count;

    /**
     * 具体的内容
     */
    public T data;

}
