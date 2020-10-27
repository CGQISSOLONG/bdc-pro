package com.bdc.Impl;


import com.bdc.dao.BdctjDao;
import com.bdc.dao.req.BdtjReq;
import com.bdc.service.BdctjService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author luoxuan
 * @time 2019/10/16 20:23
 * @Version 1.0
 * Description:
 */
@Service
public class BdctjServiceImpl implements BdctjService {

    @Resource
    private BdctjDao bdctjDao;

    @Override
    public Integer countData() {
        return bdctjDao.countData();
    }

    @Override
    public Integer countDataByDay(String date) {
       return bdctjDao.countDataByDay(date);
    }

    /**
     * 近一周汇交量
     * @return
     */
    @Override
    public Integer findYzhjCount(){ return bdctjDao.findYzhjCount(); }

    /**
     * 总业务量
     * @return
     */
    @Override
    public Integer countDataTotal(){
        return bdctjDao.countDataTotal();
    }

    /**
     * 抵押业务量
     * @return
     */
    @Override
    public Integer countDataByDyaq(){
        return bdctjDao.countDataByDyaq();
    }

    /**
     * 查封业务量
     * @return
     */
    @Override
    public Integer countDataByCfdj(){
        return bdctjDao.countDataByCfdj();
    }

    /**
     * 注销业务量
     * @return
     */
    @Override
    public Integer countDataByZxdj(){return bdctjDao.countDataByZxdj(); }

    /**
     * 各区县上报情况
     * @return
     */
    @Override
    public List<BdtjReq> countDataByArea(){ return bdctjDao.countDataByArea(null, null); }

    /**
     * 各区县上报情况
     * @return
     */
    @Override
    public List<BdtjReq> countDataByWeek(){ return bdctjDao.countDataByWeek(); }

    /**
     * 各区县一个月上报情况
     * @return
     */
    @Override
    public List<BdtjReq> countDataByMouth(){return bdctjDao.countDataByMouth();}

    /**
     * 各区一周上报情况
     * @return
     */
    @Override
    public List<BdtjReq> countDataByWeeks(){return bdctjDao.countDataByWeeks();}

    /**
     * 各区县一日上报情况
     * @return
     */
    @Override
    public List<BdtjReq> countDataByDays(){return bdctjDao.countDataByDays();}



    @Override
    public List<BdtjReq> getPieCounts(@Param("start") String start, @Param("end") String end){
        return bdctjDao.getPieCounts(start,end);
    }


}
