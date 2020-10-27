package com.bdc.controller.api;

import com.bdc.common.BdcProtection;

import com.bdc.dao.BdctjDao;
import com.bdc.dao.req.BdtjReq;
import com.bdc.service.BdctjService;
import com.bdc.util.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;



@RestController
@RequestMapping("/bdctj")
@BdcProtection
public class BdctjController extends BaseController {

    @Resource
    private BdctjService bdctjService;

    @Resource
    private BdctjDao bdctjDao;



    /***
     * @Description: 弹窗显示汇聚情况
     */
    @RequestMapping("getHjCount")
    public Object getHjCount() {
        final HashMap<String, Integer> stringIntegerHashMap = new HashMap<>(3);
        stringIntegerHashMap.put("hjzl", bdctjDao.findHjzlCount());
        stringIntegerHashMap.put("zrhj", bdctjDao.findZrhjCount());
        stringIntegerHashMap.put("yzhj",bdctjDao.findYzhjCount());
        return ResultUtil.success(stringIntegerHashMap);
    }

    /***
     * @Description: 上报总数据
     */
    @RequestMapping("getHjtjData")
    public Object getHjtjData() {
        final HashMap<String, Integer> stringIntegerHashMap = new HashMap<>(4);
        stringIntegerHashMap.put("zywl",bdctjDao.countDataTotal());
        stringIntegerHashMap.put("dyaywl",bdctjDao.countDataByDyaq());
        stringIntegerHashMap.put("cfywl",bdctjDao.countDataByCfdj());
        stringIntegerHashMap.put("zxywl",bdctjDao.countDataByZxdj());
        return ResultUtil.success(stringIntegerHashMap);
    }

    /***
     * @Description: 各区县上报情况
     */
    @RequestMapping("getHjtjQxData")
    public Object getHjtjQxData(String start, String end) {
        List<BdtjReq> countArea = null;
        if(null != start) {
            countArea = bdctjDao.countDataByArea(start, end);
        } else {
            countArea = bdctjDao.countDataByArea(null, null);
        }

        return ResultUtil.success(countArea);
    }

    /**
     * 各地区上报量占比情况-饼状图
     */
    @RequestMapping("pie")
    public Object getPieCounts(String start, String end){
        List<BdtjReq> countArea = null;
        if(null != start) {
            countArea = bdctjDao.getPieCounts(start, end);
        } else {
            countArea = bdctjDao.getPieCounts(null, null);
        }
        for (BdtjReq req : countArea) {
            if("".equals(req.getQxdm())){
                countArea.remove(req);
            }
        }
        return ResultUtil.success(countArea);
    }

    /***
     * @Description: 各区县近一周上报情况
     */
    @RequestMapping("countDataByWeek")
    public Object countDataByWeek(){
        List<BdtjReq> countDataByWeek = bdctjDao.countDataByWeek();
        return ResultUtil.success(countDataByWeek);
    }

    /**
     * 各区县一个月上报情况
     */
    @RequestMapping("countDataByMouth")
    public Object countDataByMouth(String min, String max,String interval){
        List<BdtjReq> countDataByMouth = bdctjDao.countDataByMouth();
        List<String> getCounts = new ArrayList<>();
        for (BdtjReq req : countDataByMouth) {
            if(req.getCounts()==null){
                req.setCounts("0");
                getCounts.add(req.getCounts()) ;
            }else{
            getCounts.add(req.getCounts()) ;}
        }
        Collections.sort(getCounts);
         max = Collections.max(getCounts);
         min = Collections.min(getCounts);

        int get  = Integer.parseInt(max)-Integer.parseInt(min);

        int i = 1;
        while (get/10>10){
            i = i*10;
            get = get/10;
        }
        interval =String.valueOf(i);


        return ResultUtil.success(countDataByMouth);
    }



    /**
     * 各区县一日上报情况
     */
    @RequestMapping("countDataByDays")
    public Object countDataByDays(String min,String max ,String interval){
        List<BdtjReq> countDataByDays = bdctjDao.countDataByDays();

        List<String> getCounts = new ArrayList<>();
        for (BdtjReq req : countDataByDays) {
                getCounts.add(req.getCounts()) ;
            }
        Collections.sort(getCounts);
        max = Collections.max(getCounts);
        min = Collections.min(getCounts);
        int get  = Integer.parseInt(max)-Integer.parseInt(min);
        int i = 1;
        while (get/10>10){
            i = i*10;
            get = get/10;
        }
        interval =String.valueOf(i);

        return ResultUtil.success(countDataByDays);
    }



}
