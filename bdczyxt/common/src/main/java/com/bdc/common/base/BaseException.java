package com.bdc.common.base;


import com.bdc.common.enums.ResultEnum;

/**
 * @ClassName 类名：BaseException
 * @Description 功能说明：BaseException
 */
public class BaseException extends RuntimeException{

    private Integer code;

    public BaseException() {
    }

    public BaseException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
