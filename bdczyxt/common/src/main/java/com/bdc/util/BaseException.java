package com.bdc.util;


import com.bdc.common.enums.ResultEnum;

public class BaseException extends RuntimeException {
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
