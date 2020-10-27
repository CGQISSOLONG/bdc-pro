package com.admin.controller.bdc;

import com.admin.aop.LogAopAnnotation;
import com.admin.controller.bdc.vo.PageDataVo;
import com.bdc.common.base.BaseResult;
import com.bdc.common.enums.ResultEnum;
import com.bdc.dao.UserDao;
import com.bdc.entity.Users;
import com.bdc.service.UserService;
import com.bdc.util.MD5Util;
import com.bdc.util.RequestUtils;
import com.bdc.util.ResultUtil;
import com.bdc.util.UrlConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

//import com.zhenshan.repository.primary.PubUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ClassName 类名：PubUserController
 * @Description 功能说明：bdc 用户管理
 */
@Controller
@RequestMapping("/pubUser")
@Slf4j
public class PubUserController {

    @Autowired
    private UserDao pubUserDao;

    @Autowired
    private UserService pubUserService;

    @Value("${initpassword.hjpt}")
    private String initpassword;

    /**
     * 获取所有用户
     *
     * @param model
     * @return
     */
    @GetMapping(UrlConstant.LIST)
    public String list(Model model) {
        model.addAttribute("api", "/pubUser/pages");
        return "bdc/user/list";
    }


    @PostMapping(UrlConstant.QUERY_PAGE)
    @ResponseBody
    public PageDataVo<Users> queryPage(HttpServletRequest request, Users pubUser) {
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        if (StringUtils.isBlank(start)) {
            start = "0";
        }
        if (StringUtils.isBlank(length)) {
            length = "10";
        }
        PageHelper.startPage(Integer.valueOf(start) / Integer.valueOf(length) + 1, Integer.valueOf(length));
        List<Users> list = pubUserDao.findPubUserList(pubUser);
        PageInfo<Users> pageInfo = new PageInfo<>(list);

        PageDataVo<Users> pageDataVo = new PageDataVo<>();
        pageDataVo.setData(pageInfo.getList());
        pageDataVo.setRecordsTotal(pageInfo.getTotal());
        pageDataVo.setRecordsFiltered(pageInfo.getTotal());
        return pageDataVo;
    }


    /**
     * 状态切换
     *
     * @param pubUserId
     * @param status
     */
    @PutMapping(value = UrlConstant.TOGGLESTATUS)
    @ResponseBody
    @LogAopAnnotation
    public BaseResult switchStatus(@RequestParam("pubUserId") Integer pubUserId, @RequestParam("status") Integer status) {
        if (status == 1) {
            status = 2;
        } else {
            status = 1;
        }
        pubUserDao.updateStatusById(pubUserId, status);
        return ResultUtil.success();
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @DeleteMapping(value = "/{id}" + UrlConstant.DELETE)
    @ResponseBody
    @LogAopAnnotation
    public BaseResult delete(@PathVariable("id") Integer id) {
        try {
            pubUserDao.delete(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success();
    }

    @GetMapping(UrlConstant.FORM)
    public String form(@RequestParam(value = "pubUserId", required = false) Integer pubUserId, Model model) {
        String api = "/pubUser" + UrlConstant.CREATE;
        if (pubUserId != null) {
            api = "/pubUser/" + pubUserId + UrlConstant.UPDATE;
            model.addAttribute("pubUser", pubUserDao.findOne(pubUserId));
        }
        model.addAttribute("api", api);
        return "bdc/user/form";
    }

    /**
     * 重置密码
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/{id}/rePassword")
    public String rePassword(@PathVariable("id") Integer id) {
        Users user = pubUserDao.findOne(id);
        //密码加盐处理
        String md5 = MD5Util.md5(initpassword);
        user.setPassword(md5);
        pubUserDao.save(user);
        return "redirect:/user/list";
    }


    @PostMapping(UrlConstant.CREATE)
    public String create(Users pubUser, HttpServletRequest request) {
        pubUser.setPassword(MD5Util.md5(pubUser.getPassword()));
        pubUser.setStatus(2);
        final Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        pubUser.setCreateTime(dateString);
        pubUser.setUpdateTime(date);
        pubUser.setIp(RequestUtils.getIpAddr(request));
        pubUserDao.save(pubUser);
        return "redirect:/pubUser/list";
    }

    /**
     * 用户修改
     *
     * @param pubUserId
     * @param pubUser
     * @return
     */
    @PostMapping("/{pubUserId}" + UrlConstant.UPDATE)
    @LogAopAnnotation
    public String modify(@PathVariable("pubUserId") Integer pubUserId, Users pubUser) {
        pubUserDao.updatePubUserById(pubUserId, pubUser.getAccount(), pubUser.getPassword());
        return "redirect:/pubUser/list";
    }

    /**
     * 获取角色列表
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/{id}/select-role")
    public String selectRole(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("list", pubUserService.selectRoles(id));
        model.addAttribute("api", "/pubUser/" + id + "/grant-role");
        return "bdc/user/grant-role";
    }

    /**
     * 分配角色
     *
     * @param id
     * @param rid
     * @return
     */
    @PostMapping(value = "/{id}/grant-role")
    public String grantRole(@PathVariable("id") Integer id, Integer[] rid) {
        if (rid == null) {
            rid = new Integer[0];
        }
        pubUserService.grantRole(id, Arrays.asList(rid));
        return "redirect:/pubUser/list";
    }
}
