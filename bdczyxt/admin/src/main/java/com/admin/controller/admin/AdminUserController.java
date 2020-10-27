package com.admin.controller.admin;


import com.admin.controller.admin.dto.AdminUserDto;
import com.admin.controller.admin.req.AdminUserReq;
import com.admin.controller.admin.req.UserInfoReq;
import com.admin.dao.primary.AdminUserDao;
import com.admin.entity.primary.AdminUser;
import com.admin.security.SecurityUtil;
import com.admin.service.AdminUserService;
import com.admin.service.impl.AdminMenuServiceImpl;
import com.bdc.common.base.BaseResult;
import com.bdc.common.enums.ResultEnum;
import com.bdc.util.BeanCopyUtils;
import com.bdc.util.ResultUtil;
import com.bdc.util.UrlConstant;
import com.fasterxml.jackson.databind.ObjectMapper;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;


@Controller
@RequestMapping("/user")
@Slf4j
public class AdminUserController {

    @Autowired
    protected AdminUserService userService;

    @Autowired
    protected AdminMenuServiceImpl menuService;

    @Autowired
    private AdminUserDao adminUserDao;

    @Value("${initpassword.admin}")
    private String initpassword;
    /**
     * 加载用户列表
     *
     * @param model
     * @return
     */
    @GetMapping(UrlConstant.LIST)
    public String list(Model model) {
        final List<AdminUser> list = userService.list();
        model.addAttribute("list", list);
        return "admin/user/list";
    }

    /**
     * 新增用户
     *
     * @param pubUserReq
     * @return
     */
    @PostMapping(UrlConstant.CREATE)
    public String create(AdminUserReq pubUserReq) {
        AdminUser user = new AdminUser();
        user.setUsername(user.getAccount());
        BeanCopyUtils.copyProperties(pubUserReq, user);
        userService.create(user);
        return "redirect:/user/list";
    }

    /**
     * 用户新增或更新页面
     *
     * @param adminUserId
     * @param model
     * @return
     */
    @GetMapping(UrlConstant.FORM)
    public String form(@RequestParam(value = "adminUserId", required = false) Integer adminUserId, Model model) {
        String api = "/user" + UrlConstant.CREATE;
        if (adminUserId != null) {
            model.addAttribute("pubUser", BeanCopyUtils.copyProperties(adminUserDao.findOne(adminUserId), AdminUserDto.class));
            api = "/user/" + adminUserId + UrlConstant.UPDATE;
        }
        model.addAttribute("api", api);
        return "admin/user/form";
    }

    /**
     * 用户修改
     *
     * @param id
     * @param pubUserReq
     * @return
     */
    @PostMapping(value = "/{id}" + UrlConstant.UPDATE)
    public String modify(@PathVariable("id") Integer id, AdminUserReq pubUserReq) {
        AdminUser user = adminUserDao.findOne(id);
        user.setAccount(pubUserReq.getAccount());
        user.setEmail(pubUserReq.getEmail());
        userService.modify(user);
        return "redirect:/user/list";
    }

    /**
     * 重置密码
     *
     * @param id
     * @return
     */
    @PostMapping(value = "/{id}/rePassword")
    public String rePassword(@PathVariable("id") Integer id) {
        AdminUser user = adminUserDao.findOne(id);
        //密码加盐处理
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(initpassword);
        user.setPassword(encode);
        userService.modify(user);
        return "redirect:/user/list";
    }

    /**
     * 状态切换
     *
     * @param id
     * @param status
     */
    @PutMapping(value = "/{id}" + UrlConstant.TOGGLESTATUS)
    @ResponseBody
    public void switchStatus(@PathVariable("id") Integer id, @RequestParam("status") Integer status) {
        userService.updateStatusById(id, status);
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @DeleteMapping(value = "/{id}" + UrlConstant.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer id) {
        userService.delete(id);
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
        model.addAttribute("list", userService.selectRoles(id));
        model.addAttribute("api", "/user/" + id + "/grant-role");
        return "admin/user/grant-role";
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
        userService.grantRole(id, Arrays.asList(rid));
        return "redirect:/user/list";
    }

    /**
     * 修改个人资料
     *
     * @return
     */
    @GetMapping(value = "/profile")
    public String profile(Model model) {
        model.addAttribute("api", "/user/modify-profile");
        return "admin/user/profile";
    }

    /**
     * 保存资料修改
     *
     * @param myInfo
     * @return
     */
    @PostMapping(value = "/modify-profile")
    @ResponseBody
    public BaseResult modifyProfile(@RequestBody UserInfoReq myInfo) {
        if (StringUtils.isBlank(myInfo.getPassword())) {
            return ResultUtil.error(ResultEnum.PASSWORD_IS_NULL);
        }
        if (StringUtils.isBlank(myInfo.getNewPassword())) {
            return ResultUtil.error(ResultEnum.NEW_PASSWORD_IS_NULL);
        }
        if (StringUtils.isBlank(myInfo.getConfirmNewPassword())) {
            return ResultUtil.error(ResultEnum.CONFIRM_PASSWORD_IS_NULL);
        }
        if (!StringUtils.equals(myInfo.getNewPassword(), myInfo.getConfirmNewPassword())) {
            return ResultUtil.error(ResultEnum.PASSWORD_IS_INCONSISTENT);
        }
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encode = encoder.encode(myInfo.getNewPassword());
            adminUserDao.update(encode
                    , myInfo.getEmail(), new Date(), SecurityUtil.getUserId());
            SecurityUtil.getUser().setEmail(myInfo.getEmail());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResultUtil.error(ResultEnum.UPDATE_ERROR);
        }
        return ResultUtil.success();
    }

    @RequestMapping(value = "/selectusername", method = RequestMethod.GET)
    @ResponseBody
    public String selectusername(String username) throws IOException {
        log.debug("username:{}", username);
        boolean result = false;
        List<AdminUser> listuser = adminUserDao.findUserByAccount(username);
        if (listuser.size() < 1) {
            result = true;
        }
        Map<String, Boolean> map = new HashMap<>();
        map.put("valid", result);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    @RequestMapping(value = "/selectuseremail", method = RequestMethod.GET)
    @ResponseBody
    public String selectuseremail(String email) throws IOException {
        log.debug("email:{}", email);
        boolean result = false;
        List<AdminUser> listuser = adminUserDao.findUserByEmail(email);
        if (listuser.size() < 1) {
            result = true;
        }
        Map<String, Boolean> map = new HashMap<>();
        map.put("valid", result);
        ObjectMapper mapper = new ObjectMapper();
        String resultString = "";
        try {
            resultString = mapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }


}
