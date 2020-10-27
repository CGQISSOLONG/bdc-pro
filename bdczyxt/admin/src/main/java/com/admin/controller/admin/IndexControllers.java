package com.admin.controller.admin;

import com.admin.controller.admin.vo.MonitorInfoVo;
import com.bdc.common.base.BaseResult;
import com.bdc.dao.UserDao;
import com.bdc.util.MonitorUtil;
import com.bdc.util.ResultUtil;
import com.bdc.util.UrlConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * @ClassName 类名：IndexController
 * @Description 功能说明：管理后台首页
 */
@Controller
public class IndexControllers {

    @Autowired
    private UserDao pubUserDao;


    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("api", UrlConstant.DETAIL);
        return "index";
    }

    @GetMapping(UrlConstant.DETAIL)
    @ResponseBody
    public BaseResult getResource(){
        final MonitorInfoVo indexVo = new MonitorInfoVo();
        Integer count=pubUserDao.findPubUserByCreateTime();
        indexVo.setRegisterUser(count);
        final Map<String, String> computerInfoRealTime = MonitorUtil.getComputerInfoRealTime();
        indexVo.setSystemInfo(computerInfoRealTime);
        return ResultUtil.success(indexVo);
    }
}
