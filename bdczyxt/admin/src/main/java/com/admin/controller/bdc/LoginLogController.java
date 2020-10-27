package com.admin.controller.bdc;

import com.admin.aop.LogAopAnnotation;
import com.admin.controller.bdc.req.PubUserLoginLogReq;
import com.admin.controller.bdc.vo.PageDataVo;
import com.admin.dao.primary.PubUserLoginLogDao;
import com.bdc.common.base.BaseResult;
import com.bdc.common.enums.ResultEnum;
import com.bdc.entity.primary.UserLoginLog;
import com.bdc.util.ResultUtil;
import com.bdc.util.UrlConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description 登录日志管理
 */
@Controller
@Log4j
@RequestMapping("/hjpt/loginLog")
public class LoginLogController {

    @Autowired
    private PubUserLoginLogDao loginLogDao;

    @GetMapping(UrlConstant.LIST)
    public String list(Model model) {
        model.addAttribute("api", "/hjpt/loginLog/pages");
        model.addAttribute("apiDelete", "/hjpt/loginLog/batchdelete");
        return "bdc/loginLog/list";
    }

    @PostMapping(UrlConstant.QUERY_PAGE)
    @ResponseBody
    public PageDataVo<UserLoginLog> queryPage(HttpServletRequest request, PubUserLoginLogReq logReq) {
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        if(StringUtils.isBlank(start)){
            start = "0";
        }
        if(StringUtils.isBlank(length)){
            length = "10";
        }
        PageHelper.startPage(Integer.valueOf(start)/ Integer.valueOf(length)+1,Integer.valueOf(length));
        List<UserLoginLog> list = loginLogDao.findLogList(logReq);
        PageInfo<UserLoginLog> pageInfo = new PageInfo<>(list);

        PageDataVo<UserLoginLog> pageDataVo=new PageDataVo<>();
        pageDataVo.setData(pageInfo.getList());
        pageDataVo.setRecordsTotal(pageInfo.getTotal());
        pageDataVo.setRecordsFiltered(pageInfo.getTotal());
        return pageDataVo;
    }

    @DeleteMapping(UrlConstant.BATCH_DELETE)
    @ResponseBody
    @LogAopAnnotation
    public BaseResult clear() {
        try {
            loginLogDao.deleteAll();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success();
    }

    @DeleteMapping("/{id}"+ UrlConstant.DELETE)
    @ResponseBody
    @LogAopAnnotation
    public BaseResult delete(@PathVariable Integer id) {
        try {
            loginLogDao.delete(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success();
    }
}
