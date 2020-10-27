package com.bdc.controller.api;

import com.bdc.common.BdcProtection;
import com.bdc.common.base.BaseResult;
import com.bdc.dao.ApiLtLogDao;
import com.bdc.dao.HjExceptionDao;
import com.bdc.dao.HjjrqdDao;
import com.bdc.dao.LoginLogDao;
import com.bdc.dao.dto.HjjrqdDto;
import com.bdc.dao.req.HjjrqdReq;
import com.bdc.entity.ApiLtLog;
import com.bdc.entity.HjExceptionLog;
import com.bdc.entity.primary.UserLoginLog;
import com.bdc.util.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName 类名：JkjgController
 * @Description 功能说明：监控监管
 */
@Api(tags = "监控监管")
@RestController
@BdcProtection
@RequestMapping("/jkjg")
@Slf4j
public class JkjgController extends BaseController {

    @Autowired
    private ApiLtLogDao apiLtLogDao;

    @Autowired
    private LoginLogDao loginLogDao;

    @Autowired
    private HjExceptionDao hjExceptionDao;

    @Autowired
    private HjjrqdDao hjjrqdDao;

    @RequestMapping("/ltQuery")
    public BaseResult ltQuery(HttpServletRequest request) {
        //创建时间降序排序
        Sort sort = new Sort(Sort.Direction.DESC, "cxsj");
        final String page = request.getParameter("page");
        final String limit = request.getParameter("limit");
        Pageable pageable = new PageRequest(Integer.valueOf(page) - 1, Integer.valueOf(limit), sort);
        Page<ApiLtLog> ltLogPage = apiLtLogDao.findAll(pageable);
        final List<ApiLtLog> content = ltLogPage.getContent();
        for (ApiLtLog apiLtLog : content) {
            switch (apiLtLog.getStatus()) {
                case 0:
                    apiLtLog.setStatusStr("系统异常");
                    break;
                case 1:
                    apiLtLog.setStatusStr("正常返回");
                    break;
                case 2:
                    apiLtLog.setStatusStr("验证失败");
                    break;
                default:
                    apiLtLog.setStatusStr("参数异常");
                    break;
            }
        }
        return ResultUtil.success(content, ltLogPage.getTotalElements());
    }

    @RequestMapping("/loginLog")
    public BaseResult loginLog(HttpServletRequest request) {
        final String page = request.getParameter("page");
        final String limit = request.getParameter("limit");
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<UserLoginLog> all = loginLogDao.findAlls();
        PageInfo<UserLoginLog> pageInfo = new PageInfo<>(all);
        return ResultUtil.success(pageInfo.getList(), pageInfo.getTotal());
    }

    @RequestMapping("/HjExceptionLog")
    public BaseResult HjExceptionLog(HttpServletRequest request) {
        final String page = request.getParameter("page");
        final String limit = request.getParameter("limit");
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<HjExceptionLog> list = hjExceptionDao.findAllLog();
        PageInfo<HjExceptionLog> pageInfo = new PageInfo<>(list);
        return ResultUtil.success(pageInfo.getList(), pageInfo.getTotal());
    }


    /**
     * 导出全部登陆日志数据
     *
     * @param request
     * @return
     */
    @RequestMapping("/getAllLogData")
    public BaseResult getAllLogData(HttpServletRequest request) {
        final List<UserLoginLog> all = loginLogDao.findAlls();
        return ResultUtil.success(all);
    }

    /**
     * 导出全部龙腾查询日志数据
     *
     * @param request
     * @return
     */
    @RequestMapping("/getAllLtQueryData")
    public BaseResult getAllData(HttpServletRequest request) {
        final List<ApiLtLog> all = apiLtLogDao.findAlls();
        return ResultUtil.success(all);
    }

    /**
     * 导出全部汇聚异常日志数据
     * @return
     */
    @RequestMapping("/getHjExceptionLogAll")
    public BaseResult getHjExceptionLogAll(){
        List<HjExceptionLog> list = hjExceptionDao.findAllLog();
        return ResultUtil.success(list);
    }

    @PostMapping("/getHjjrqdData")
    public BaseResult getHjjrqdData(HttpServletRequest request, HjjrqdReq hjjrqdReq) {
        final String page = request.getParameter("page");
        final String limit = request.getParameter("limit");
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<HjjrqdDto> list = hjjrqdDao.getHjjrqdData(hjjrqdReq);
        PageInfo<HjjrqdDto> pageInfo = new PageInfo<>(list);
        return ResultUtil.success(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 导出查询清单数据
     * @return
     */
    @RequestMapping("/getQdDataAll")
    public BaseResult getQdDataAll(HjjrqdReq hjjrqdReq){
        List<HjjrqdDto> list = hjjrqdDao.getHjjrqdData(hjjrqdReq);
        return ResultUtil.success(list);
    }
}
