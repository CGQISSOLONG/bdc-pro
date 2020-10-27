package com.bdc.controller.api;

import com.bdc.common.BdcProtection;
import com.bdc.common.base.BaseResult;
import com.bdc.dao.HjfxDao;
import com.bdc.dao.dto.*;
import com.bdc.util.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName 类名：HjfxController
 * @Description 功能说明：汇交分析
 */
@Api(tags = "汇交分析")
@RestController
@BdcProtection
@RequestMapping("/hjfx")
public class HjfxController extends BaseController {
    @Autowired
    private HjfxDao hjfxDao;

    /**
     * 柱状图数据-网上申请比例
     *
     * @param
     * @return
     */
    @ApiOperation(value = "汇交分析-网上申请比例", notes = "网上申请比例")
    @PostMapping("/getOnlineRatioData")
    public BaseResult getOnlineRatioData(String sj) {
        List<Map<String, Integer>> list = hjfxDao.getWsqlData(sj);
        return ResultUtil.success(list);
    }

    /**
     * 柱状图数据-数据汇交一致率
     *
     * @param
     * @return
     */
    @ApiOperation(value = "汇交分析-数据汇交一致率", notes = "数据汇交一致率")
    @PostMapping("/getHjYzRateData")
    public BaseResult getHjYzRateData(String sj) {
        List<Map<String, Integer>> list = hjfxDao.getHjYzRateData(sj);
        return ResultUtil.success(list);
    }

    /**
     * 柱状图数据-发证平均用时
     *
     * @param
     * @return
     */
    @ApiOperation(value = "汇交分析-发证平均用时", notes = "发证平均用时")
    @PostMapping("/getAverageTimeData")
    public BaseResult getAverageTimeData(String sj) {
        List<Map<String, Integer>> list = hjfxDao.getYsData(sj);
        return ResultUtil.success(list);
    }

    /**
     * 表格数据-数据汇交一致率
     *
     * @param
     * @return
     */
    @ApiOperation(value = "汇交分析-数据汇交一致率", notes = "数据汇交一致率")
    @PostMapping("/getFormHjYzRateData")
    public BaseResult getFormHjYzRateData(String sj) {
        List<HjyzrateDto> list = hjfxDao.getFormHjYzRateData(sj);
       // int i = 1/0;
        return ResultUtil.success(list, Long.valueOf(list.size()));
    }
    /**
     * 表格数据-数据汇交一致率
     *
     * @param
     * @return
     */
    @ApiOperation(value = "汇交分析-数据汇交一致率", notes = "数据汇交一致率与决策系统对比")
    @PostMapping("/getYzlJcData")
    public BaseResult getYzlJcData(String sj,String qxdm) {
        if (StringUtils.isBlank(sj) || StringUtils.isBlank(qxdm)) {
            return ResultUtil.success();
        }
        List<YzlJcSystemDto> list = hjfxDao.getYzlJcData(sj,qxdm);
        return ResultUtil.success(list, Long.valueOf(list.size()));
    }

    /**
     * 表格数据-网上申请
     *
     * @param
     * @return
     */
    @ApiOperation(value = "汇交分析-网上申请", notes = "网上申请")
    @PostMapping("/getFormOnlineData")
    public BaseResult getFormOnlineData(String sj) {
        List<OnlineratioDto> list = hjfxDao.getFormOnlineData(sj);
        return ResultUtil.success(list, Long.valueOf(list.size()));
    }

    /**
     * 表格数据-用时
     *
     * @param
     * @return
     */
    @ApiOperation(value = "汇交分析-用时", notes = "用时")
    @PostMapping("/getFormYsData")
    public BaseResult getFormYsData(String sj) {
        List<AverageTimeDto> list = hjfxDao.getFormYsData(sj);
        for (AverageTimeDto averageTimeDto : list) {
            if(Double.parseDouble(averageTimeDto.getMidUse())<0){
                averageTimeDto.setEvaluationScore("0");
            }else if(Double.parseDouble(averageTimeDto.getMidUse())<=1){
                averageTimeDto.setEvaluationScore("100");
            }else{
                double  source = 100 - 10*(Double.parseDouble(averageTimeDto.getMidUse())-1);
                averageTimeDto.setEvaluationScore(String.format("%.2f",source));
            }
        }
        return ResultUtil.success(list, Long.valueOf(list.size()));
    }

    @ApiOperation(value = "汇交分析-用时", notes = "用时明细")
    @PostMapping("/getFormYsTotalDataMx")
    public BaseResult getFormYsTotalDataMx(String sj,String qxdm, HttpServletRequest request) {
       final String page = request.getParameter("page");
       final String limit = request.getParameter("limit");
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<HjfxMx> list = hjfxDao.getFormYsTotalDataMx(sj,Integer.parseInt(qxdm));
        PageInfo<HjfxMx> pageInfo = new PageInfo<>(list);
        return ResultUtil.success(pageInfo.getList(), pageInfo.getTotal());
    }


    @ApiOperation(value = "汇交分析-网上申请", notes = "网上申请明细")
    @PostMapping("/getFormOnlineWebTotalDataMx")
    public BaseResult getFormOnlineWebTotalDataMx(String sj,String qxdm ,HttpServletRequest request) {
        final String page = request.getParameter("page");
        final String limit = request.getParameter("limit");
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<HjfxMx> list = hjfxDao.getFormOnlineWebTotalDataMx(sj,Integer.parseInt(qxdm));
        PageInfo<HjfxMx> pageInfo = new PageInfo<>(list);
        return ResultUtil.success(pageInfo.getList(), pageInfo.getTotal());
    }


    @ApiOperation(value = "汇交分析-累计总业务", notes = "累计申请")
    @PostMapping("/getFormOnlineTotalDataMx")
    public BaseResult getFormOnlineTotalDataMx(String sj,String qxdm, HttpServletRequest request) {
        final String page = request.getParameter("page");
        final String limit = request.getParameter("limit");
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<HjfxMx> list = hjfxDao.getFormOnlineTotalDataMx(sj,Integer.parseInt(qxdm));
        PageInfo<HjfxMx> pageInfo = new PageInfo<>(list);
        return ResultUtil.success(pageInfo.getList(), pageInfo.getTotal());
    }



    /**
     * 数据汇交一致率-报文接入率明细
     * @param sj
     * @param qxdm
     * @return
     */
    @ApiOperation(value = "数据汇交一致率", notes = "数据汇交一致率-报文接入量明细")
    @PostMapping("/getBwjrlMx")
    public BaseResult getBwjrlMx(String sj, String qxdm, HttpServletRequest request) {
        final String page = request.getParameter("page");
        final String limit = request.getParameter("limit");
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<BwmxDto> list = hjfxDao.getBwjrMx(sj, Integer.parseInt(qxdm));
        PageInfo<BwmxDto> pageInfo = new PageInfo<>(list);
        return ResultUtil.success(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 数据汇交一致率-报文接入率明细
     * @param sj
     * @param qxdm
     * @return
     */
    @ApiOperation(value = "数据汇交一致率", notes = "数据汇交一致率-其他明细")
    @PostMapping("/getSjhjMxOther")
    public BaseResult getSjhjMxOther(String sj, String qxdm, HttpServletRequest request) {
        final String page = request.getParameter("page");
        final String limit = request.getParameter("limit");
        PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        List<SjhjMxOtherDto> list = hjfxDao.getSjhjMxOther(sj, Integer.parseInt(qxdm));
        PageInfo<SjhjMxOtherDto> pageInfo = new PageInfo<>(list);
        return ResultUtil.success(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 报文接入量下载Excel
     * @param sj
     * @param qxdm
     * @return
     */
    @RequestMapping("/geBwjrlMxAll")
    public BaseResult geBwjrlMxAll(String sj, String qxdm) {
        List<BwmxDto> list = hjfxDao.getBwjrMx(sj, Integer.parseInt(qxdm));
        return ResultUtil.success(list);
    }

    @RequestMapping("/geSjhjOtherMxAll")
    public BaseResult geSjhjOtherMxAll(String sj, String qxdm) {
        List<SjhjMxOtherDto> list = hjfxDao.getSjhjMxOther(sj, Integer.parseInt(qxdm));
        return ResultUtil.success(list);
    }


    @RequestMapping("/getFormOnlineTotalDataMxAll")
    public BaseResult getFormOnlineTotalDataMxAll(String sj, String qxdm) {
        List<HjfxMx> list = hjfxDao.getFormOnlineTotalDataMx(sj, Integer.parseInt(qxdm));
        return ResultUtil.success(list);
    }


    @RequestMapping("/getFormOnlineWebTotalDataMxAll")
    public BaseResult getFormOnlineWebTotalDataMxAll(String sj, String qxdm) {
        List<HjfxMx> list = hjfxDao.getFormOnlineWebTotalDataMx(sj, Integer.parseInt(qxdm));
        return ResultUtil.success(list);
    }


    @RequestMapping("/getFormYsTotalDataMxAll")
    public BaseResult getFormYsTotalDataMxAll(String sj, String qxdm) {
        List<HjfxMx> list = hjfxDao.getFormYsTotalDataMx(sj, Integer.parseInt(qxdm));
        return ResultUtil.success(list);
    }

}
