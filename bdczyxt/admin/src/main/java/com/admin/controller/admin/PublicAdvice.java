package com.admin.controller.admin;


import com.admin.entity.primary.AdminMenu;
import com.admin.security.SecurityUser;
import com.admin.security.SecurityUtil;
import com.admin.service.AdminMenuService;
import com.bdc.dao.ErrorLogDao;
import com.bdc.entity.primary.ErrorLog;
import com.bdc.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @ClassName 类名：PublicAdvice
 * @Description 功能说明：统一异常处理、数据绑定
 */
@ControllerAdvice
@Slf4j
public class PublicAdvice {

    @Autowired
    private ErrorLogDao errorLogDao;

    @Autowired
    protected AdminMenuService adminMenuService;

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleControllerException(HttpServletRequest request, HttpSession session,
                                                  HttpServletResponse response, Exception ex)
            throws IOException {
        log.error(ex.getMessage(),ex);
        ErrorLog errorLog = new ErrorLog();
        errorLog.setIp(RequestUtils.getIpAddr(request));

        ModelAndView mv = new ModelAndView("error");
        SecurityUser user = SecurityUtil.getUser();
        if (user != null) {
            mv.addObject("user", user);
            mv.addObject("navs", adminMenuService.getNavMenus(user.getUserId()));
            errorLog.setAccount(user.getAccount());
        }
        //判断异常类型，返回响应信息
        if (ex instanceof Exception) {
            mv.addObject("code", "001");
            mv.addObject("msg", "系统异常");
            errorLog.setErrorCode("001");
        }
        mv.addObject("errorInfo", getExceptionAllinformation(ex));
        errorLog.setErrorMsg(ex.getMessage() + "----" + getExceptionAllinformation(ex));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(new Date());
        System.out.println(dateString);
        errorLog.setExceptionTime(dateString);
        errorLog.setType(1);
        errorLog.setClassName(getExceptionAllinformation(ex).split("\\(")[0]);
        errorLog.setInterfaceUrl(request.getServletPath());
        errorLog.setMethods(request.getMethod());
        /*if(null != request.getParameterMap()) {
            StringBuilder param = new StringBuilder();
            request.getParameterMap().forEach((k,v)->{
                param.append(k + ":" + request.getParameter(k) + ";");
            });
            errorLog.setParams(param.toString());
        }*/
        errorLog.setParams(request.getQueryString());
        errorLogDao.save(errorLog);
        return mv;
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
        if (s.getClassName().contains("com.admin")) {
            return true;
        }
        if (s.getMethodName().contains("com.admin")) {
            return true;
        }
        if (s.getClassName().contains("com.bdc")) {
            return true;
        }
        if (s.getMethodName().contains("com.bdc")) {
            return true;
        }
        return false;
    }

    @ModelAttribute
    public void addCommonModel(Model model, HttpServletRequest request) {
        SecurityUser user = SecurityUtil.getUser();
        if (user != null) {
            model.addAttribute("user", user);
            final List<AdminMenu> navMenus = adminMenuService.getNavMenus(user.getUserId());
            model.addAttribute("navs", navMenus);
        }

    }
}
