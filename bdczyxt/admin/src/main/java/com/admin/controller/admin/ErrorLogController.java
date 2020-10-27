package com.admin.controller.admin;

import com.admin.aop.LogAopAnnotation;
import com.admin.controller.bdc.vo.PageDataVo;
import com.bdc.common.base.BaseResult;
import com.bdc.common.enums.ResultEnum;
import com.bdc.dao.ErrorLogDao;
import com.bdc.dao.req.ErrorLogReq;
import com.bdc.entity.primary.ErrorLog;
import com.bdc.util.ResultUtil;
import com.bdc.util.UrlConstant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
//import com.zhenshan.repository.primary.ErrorLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/errorLog")
@Slf4j
public class ErrorLogController {

    @Autowired
    private ErrorLogDao errorLogDao;

    @GetMapping(UrlConstant.LIST)
    public String list(Model model) {
        model.addAttribute("api", "/errorLog/pages");
        model.addAttribute("apiDelete", "/errorLog/batchdelete");
        return "admin/errorLog/list";
    }

    @DeleteMapping(UrlConstant.BATCH_DELETE)
    @ResponseBody
    @LogAopAnnotation
    public BaseResult clear() {
        try {
            errorLogDao.deleteAll();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success();
    }

    @DeleteMapping("/{id}"+ UrlConstant.DELETE)
    @ResponseBody
    @LogAopAnnotation
    public BaseResult delete(@PathVariable("id") String id) {
        try {
            errorLogDao.deletes(Integer.parseInt(id));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResultUtil.error(ResultEnum.DELETE_ERROR);
        }
        return ResultUtil.success();
    }


    @PostMapping(UrlConstant.QUERY_PAGE)
    @ResponseBody
    public PageDataVo<ErrorLog> queryPage(HttpServletRequest request, ErrorLogReq errorLogReq) {
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        if(StringUtils.isBlank(start)){
            start = "0";
        }
        if(StringUtils.isBlank(length)){
            length = "10";
        }
        PageHelper.startPage(Integer.valueOf(start)/ Integer.valueOf(length)+1,Integer.valueOf(length));
        List<ErrorLog> list =errorLogDao.findLogList(errorLogReq);
        PageInfo<ErrorLog> pageInfo = new PageInfo<>(list);

        PageDataVo<ErrorLog> pageDataVo=new PageDataVo<>();
        pageDataVo.setData(pageInfo.getList());
        pageDataVo.setRecordsTotal(pageInfo.getTotal());
        pageDataVo.setRecordsFiltered(pageInfo.getTotal());
        return pageDataVo;
    }

    @GetMapping("/{id}" + UrlConstant.DETAIL)
    @LogAopAnnotation
    public String detail(@PathVariable("id")Integer id , Model model) {
        ErrorLog errorLog = errorLogDao.findOne(id);
        model.addAttribute("errorLog", errorLog);
        return "admin/errorLog/form";
    }
}
