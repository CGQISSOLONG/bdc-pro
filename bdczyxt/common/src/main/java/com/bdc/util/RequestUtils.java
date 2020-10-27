package com.bdc.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName 类名：RequestUtils
 * @Description 功能说明：请求工具类
 */
public class RequestUtils {
    /**
     * 获取ip工具类，除了getRemoteAddr，其他ip均可伪造
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        // 网宿cdn的真实ip
        String ip = request.getHeader("Cdn-Src-Ip");
        // 蓝讯cdn的真实ip
        if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        // 获取代理ip
        if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        // 获取代理ip
        if (ip == null || ip.length() == 0 || " unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        // 获取代理ip
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        // 获取真实ip
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
