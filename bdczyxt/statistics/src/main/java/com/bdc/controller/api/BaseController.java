package com.bdc.controller.api;

import com.alibaba.fastjson.JSON;
import com.bdc.common.base.BaseResult;
import com.bdc.common.base.DataException;
import com.bdc.common.enums.ResultEnum;
import com.bdc.dao.ErrorLogDao;
import com.bdc.entity.primary.ErrorLog;
import com.bdc.util.BaseException;
import com.bdc.util.RequestUtils;
import com.bdc.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class BaseController {
    @Autowired
    private ErrorLogDao errorLogDao;

    /**
     * 统一异常处理
     *
     * @param e
     */
    @ExceptionHandler()
    public Object exceptionHandler(Exception e, HttpServletRequest request, HttpSession session) {
        ErrorLog errorLog = new ErrorLog();
        errorLog.setType(0);
        errorLog.setIp(RequestUtils.getIpAddr(request));
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dates = simpleDateFormat.format(date);
        errorLog.setExceptionTime(dates);
        errorLog.setMethods(request.getMethod());
        errorLog.setParams(request.getQueryString());
        errorLog.setClassName(getExceptionAllinformation(e).split("\\(")[0]);
        errorLog.setInterfaceUrl(request.getServletPath());
        if(null != session.getAttribute("username")) {
            errorLog.setAccount(session.getAttribute("username") + "");
        }
        if (e instanceof BaseException) {
            //全局基类自定义异常,返回{code,msg}
            BaseException baseException = (BaseException) e;
            errorLog.setErrorCode(baseException.getCode() + "");
            errorLog.setErrorMsg(baseException.getMessage() + "----" + getExceptionAllinformation(e));
            errorLogDao.save(errorLog);
            return ResultUtil.error(baseException);
        } else if (e instanceof DataException) {
            //数据异常，异常情况，携带数据返回
            DataException dataException = (DataException) e;
            //异常数据结果
            String message = dataException.getMessage();
            errorLog.setErrorMsg(message + "----" + getExceptionAllinformation(e));
            errorLogDao.save(errorLog);
            return JSON.parseObject(message, BaseResult.class);
        } else {
            log.error("系统异常: {}", e);
            errorLog.setErrorCode("-1");
            errorLog.setErrorMsg(e.getMessage() + "----" + getExceptionAllinformation(e));
            errorLogDao.save(errorLog);
            return ResultUtil.error(ResultEnum.UNKNOWN_ERROR);
        }
    }

    private static String getExceptionAllinformation(Exception e) {
        StackTraceElement[] trace = e.getStackTrace();
        StringBuilder stringBuilder = new StringBuilder();
        for (StackTraceElement s : trace) {
            if (methodAndClass(s)) {
                stringBuilder.append(s + "\n");
            }
        }
        return stringBuilder.toString();
    }

    private static boolean methodAndClass(StackTraceElement s) {
        if (s.getClassName().contains("com.bdc")) {
            return true;
        }
        if (s.getMethodName().contains("com.bdc")) {
            return true;
        }
        return false;
    }
}
