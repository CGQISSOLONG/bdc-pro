package com.admin.controller.admin.intercept;

import com.admin.dao.primary.AdminMenuDao;
import com.admin.entity.primary.AdminMenu;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.parameters.P;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @ClassName 类名：NavMenuActiveInterceptor
 * @Description 功能说明：菜单导航拦截适配器
 */
public class NavMenuActiveInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AdminMenuDao adminMenuDao;

    //private static AdminMenuRepository adminmenurepository = SpringContextHolder.getBean(AdminMenuRepository.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String id=request.getParameter("id");
        request.setAttribute("currentMenu",id);
        if(id!=null){
            AdminMenu adminMenu = adminMenuDao.findOne(Integer.valueOf(id));
            request.setAttribute("parentId", adminMenu.getParentId());
        }
        return super.preHandle(request, response, handler);
    }
}
