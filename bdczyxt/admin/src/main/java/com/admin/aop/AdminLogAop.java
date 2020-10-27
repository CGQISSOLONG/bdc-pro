package com.admin.aop;

import com.admin.dao.primary.AdminLogDao;
import com.admin.entity.primary.AdminLog;
import com.admin.security.SecurityUser;
import com.admin.security.SecurityUtil;
import com.bdc.util.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class AdminLogAop {

    @Autowired
    private AdminLogDao adminLogDao;

    @Pointcut("@annotation(com.admin.aop.LogAopAnnotation)")
    private void pointCutMethod() {
    }

    /**
     * 记录操作日志
     * 使用上面定义的切入点
     */
    @After("pointCutMethod()")
    public void recordLog(JoinPoint joinPoint) {
        Long start = System.currentTimeMillis();
        final SecurityUser user = SecurityUtil.getUser();
        AdminLog adminLog = new AdminLog();
        if (user == null) {
            log.error("用户信息为空");
        } else {
            adminLog.setUserId(user.getUserId());
            adminLog.setOperation(user.getUsername());
            adminLog.setUserName(user.getUsername());
        }
        try {
            HttpServletRequest request =
                    ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            final Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dates = simpleDateFormat.format(date);
            adminLog.setIp(RequestUtils.getIpAddr(request));
            adminLog.setUrl(request.getRequestURI());
            adminLog.setCreateTime(dates);
            adminLogDao.save(adminLog);
        } catch (Exception e) {
            log.error("插入日志异常", e.getMessage());
        }
        Long end = System.currentTimeMillis();
        log.info("记录日志消耗时间:" + (end - start) / 1000);
    }
}
