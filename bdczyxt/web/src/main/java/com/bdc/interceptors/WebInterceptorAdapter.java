package com.bdc.interceptors;

import com.bdc.common.PassLogin;
import com.bdc.dao.MenuDao;
import com.bdc.entity.Menu;
import com.bdc.entity.Users;
import com.bdc.service.UserService;
import com.bdc.util.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @ClassName 类名：WebInterceptorAdapter
 * @Description 功能说明：拦截器适配器
 * spring 拦截器
 * 1、HandlerInterceptorAdapter抽象类(该类实现AsyncHandlerInterceptor接口，AsyncHandlerInterceptor接口继承了HandlerInterceptor接口)
 * 需要继承，HandlerInterceptor接口需要实现
 * 可以作为日志记录和登录校验来使用
 * 2、建议使用HandlerInterceptorAdapter，因为可以按需进行方法的覆盖。
 * 3、
 * preHandle在业务处理器处理请求之前被调用。预处理，可以进行编码、登录校验、安全控制等处理；
 * postHandle在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回ModelAndView，但未进行页面渲染），有机会修改ModelAndView；
 * afterCompletion在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面），可以根据ex是否为null判断是否发生了异常，进行日志记录；

 */
public class WebInterceptorAdapter extends HandlerInterceptorAdapter {

    private static UserService userService = SpringContextHolder.getBean(UserService.class);

    private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);


    /**
     * 拦截于请求刚进入时，进行判断，需要boolean返回值，如果返回true将继续执行，
     * 如果返回false，将不进行执行。一般用于登录校验。
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler != null) {
            if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
                final Method method = ((HandlerMethod) handler).getMethod();
                Class clazz = method.getDeclaringClass();
                //类注解免登
                if (clazz.isAnnotationPresent(PassLogin.class)) {
                    return super.preHandle(request, response, handler);
                }
                //方法注解免登
                if(method.isAnnotationPresent(PassLogin.class)){
                    return super.preHandle(request, response, handler);
                }
            }
        }

        final HttpSession session = request.getSession();
        Object token = session.getAttribute("token");
        //判断请求是否为ajax请求
        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        Users t=null;
        if (token == null || StringUtils.isBlank(token.toString()) || token.toString().length() < 32) {
            if (isAjax) {
                write(response, "登录超时");
            } else {
                //修改被拦截返回地址 edit by laiqidong
                response.sendRedirect(request.getContextPath() + "/user/loginPage");
                System.out.println(request.getContextPath());
            }
            return false;
        } else {
            t = userService.getPubUserByToken(token.toString());
            if (t == null || t.getStatus() == 2) {
                session.removeAttribute("token");
                if (isAjax) {
                    write(response, "登录超时");
                } else {
                    //修改被拦截返回地址 edit by laiqidong
                    response.sendRedirect(request.getContextPath() + "/user/loginPage");
                }
                return false;
            }
        }

        List<Menu> list=(List<Menu>) Menu.buildTree(menuDao.findMenubyUserId2(t.getId(),"1"));
        session.setAttribute("menu", list);
        session.setAttribute("user", t);
        return super.preHandle(request, response, handler);
    }

    public void write(HttpServletResponse response, String obj) {
        Writer writer;
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            writer = response.getWriter();
            writer.write(obj);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
