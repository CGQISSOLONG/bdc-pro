package com.admin.controller.admin;


import com.admin.aop.LogAopAnnotation;
import com.admin.controller.admin.req.AdminLogReq;
import com.admin.controller.bdc.vo.PageDataVo;
import com.admin.dao.primary.AdminLogDao;
import com.admin.entity.primary.AdminLog;
import com.bdc.common.base.BaseResult;
import com.bdc.common.enums.ResultEnum;
import com.bdc.util.ResultUtil;
import com.bdc.util.UrlConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/log")
@Slf4j
public class AdminLogController {


    @Autowired
    private AdminLogDao adminLogDao;

    @DeleteMapping(UrlConstant.BATCH_DELETE)
    @ResponseBody
    @LogAopAnnotation
    public BaseResult clear() {
        try {
            adminLogDao.deleteAll();
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
            adminLogDao.delete(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success();
    }

    @GetMapping(UrlConstant.LIST)
    public String list(Model model) {
        model.addAttribute("api", "/log/pages");
        model.addAttribute("apiDelete", "/log/batchdelete");
        return "admin/log/list";
    }

    @PostMapping(UrlConstant.QUERY_PAGE)
    @ResponseBody
    public PageDataVo<AdminLog> queryPage(HttpServletRequest request, AdminLogReq adminLogReq) {
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        if(StringUtils.isBlank(start)){
            start = "0";
        }
        if(StringUtils.isBlank(length)){
            length = "10";
        }
        PageHelper.startPage(Integer.valueOf(start)/ Integer.valueOf(length)+1,Integer.valueOf(length));
        List<AdminLog> list =adminLogDao.findLogList(adminLogReq);
        PageInfo<AdminLog> pageInfo = new PageInfo<>(list);

        PageDataVo<AdminLog> pageDataVo=new PageDataVo<>();
        pageDataVo.setData(pageInfo.getList());
        pageDataVo.setRecordsTotal(pageInfo.getTotal());
        pageDataVo.setRecordsFiltered(pageInfo.getTotal());
        return pageDataVo;
    }
}
