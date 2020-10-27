package com.bdc.util;

import com.bdc.common.base.BaseResult;
import com.bdc.common.enums.ResultEnum;

public class ResultUtil {

    /**
     * 访问成功时调用 包含data
     * @param object
     * @return
     */
    public static BaseResult success(Object object){
        BaseResult result = new BaseResult();
        result.setCode(200);
        result.setMsg("success");
        result.setData(object);
        return result;
    }
    /**
     * 访问成功时调用 包含data且返回总数
     * @param object
     * @return
     */
    public static BaseResult success(Object object,Long count){
        BaseResult result = new BaseResult();
        result.setCode(200);
        result.setMsg("success");
        result.setData(object);
        result.setCount(count);
        return result;
    }

    /**
     * 访问成功时调用 不包含data
     * @return
     */
    public static BaseResult success(){
        return success(null);
    }


    /**
     * 返回异常情况 不包含data
     * @param code
     * @param msg
     * @return
     */
    public static BaseResult error(Integer code,String msg){
        BaseResult result = new BaseResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 返回异常情况 包含data
     * @param resultEnum
     * @param object
     * @return
     */
    public static BaseResult error(ResultEnum resultEnum, Object object){
        BaseResult result = error(resultEnum);
        result.setData(object);
        return result;
    }

    /**
     * 全局基类自定义异常 异常处理
     * @param e
     * @return
     */
    public static BaseResult error(BaseException e){
        return error(e.getCode(),e.getMessage());
    }

    /**
     * 返回异常情况 不包含data
     * @param resultEnum
     * @return
     */
    public static BaseResult error(ResultEnum resultEnum){
        return error(resultEnum.getCode(),resultEnum.getMsg());
    }
}
