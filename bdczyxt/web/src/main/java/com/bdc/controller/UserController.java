package com.bdc.controller;

import com.bdc.common.BdcProtection;
import com.bdc.common.Constants;
import com.bdc.common.PassLogin;
import com.bdc.common.base.BaseResult;
import com.bdc.common.enums.ResultEnum;
import com.bdc.controller.api.BaseController;
import com.bdc.controller.req.UserReq;
import com.bdc.dao.LoginLogDao;
import com.bdc.entity.Users;
import com.bdc.entity.primary.UserLoginLog;
import com.bdc.service.UserService;
import com.bdc.util.MD5;
import com.bdc.util.MD5Util;

import com.bdc.util.ResultUtil;
import com.bdc.util.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private LoginLogDao loginLogDao;

//    @Autowired
//    private UserLoginLogDao loginLogDao;

    @GetMapping("loginPage")
    @PassLogin
    public String loginPage(){
        return "bdcs/login";
    }

    @PostMapping(UrlConstant.LOGIN)
    @PassLogin
    @ResponseBody
    public BaseResult login(UserReq userReq, HttpServletRequest request, HttpSession session){

        Users user = userService.findByName(userReq.getAccount());
        if(user == null){
            return ResultUtil.error(ResultEnum.LOGIN_ACCOUNT_IS_NULL);
        }else {
            //用户被禁用
            if(Constants.USER_STATUS_DISABLE.equals(user.getStatus())){
                return ResultUtil.error(ResultEnum.ACCOUNT_DISABLE_ERROR);
            }
            //用户被删
            if (Constants.USER_STATUS_DELETE.equals(user.getStatus())) {
                return ResultUtil.error(ResultEnum.LOGIN_ACCOUNT_IS_NULL);
            }
            if (!MD5Util.md5(userReq.getPassword()).equals(user.getPassword())) {
                return ResultUtil.error(ResultEnum.LOGIN_PASSWORD_ERROR);
            }
            Map map = new HashMap();
            user.setToken(MD5.getInstance().getMD5(user.getAccount())+System.currentTimeMillis()+Constants.MD5_KEY);
            System.out.println("****************"+user.getToken());
            map.put("user",user);
            session.setAttribute("username",user.getUsername());
            //更新最后登陆时间
            final Date loginTime = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dates = simpleDateFormat.format(loginTime);
            user.setLastLoginDate(dates);
            try {
                userService.save(user);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            session.setAttribute("token", user.getToken());
            final UserLoginLog pubUserLoginLog = new UserLoginLog();
            pubUserLoginLog.setAccount(user.getAccount());
            pubUserLoginLog.setIp(RequestUtils.getIpAddr(request));
            pubUserLoginLog.setUserId(user.getId());
            pubUserLoginLog.setLoginTime(dates);
            pubUserLoginLog.setCreateDate(dates);
            // 增加用户登录时浏览器信息
            pubUserLoginLog.setBrowserName(userReq.getBrowserName());
            pubUserLoginLog.setBrowserVersion(userReq.getBrowserVersion());
            loginLogDao.save(pubUserLoginLog);
            return ResultUtil.success(map);
        }

    }


    @ApiOperation(value = "退出登陆", notes = "退出登陆")
    @GetMapping("logout")
    @BdcProtection
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){

        final HttpSession session = request.getSession();
        session.removeAttribute("token");
        session.removeAttribute("menu");
        session.removeAttribute("user");
        return "bdcs/login";

    }
}
