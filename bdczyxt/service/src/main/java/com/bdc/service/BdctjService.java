package com.bdc.service;

import com.bdc.dao.req.BdtjReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author luoxuan
 * @time 2019/10/16 20:22
 * @Version 1.0
 * Description: 不动产基础统计
 */

public interface BdctjService {

    Integer countData();

    Integer countDataByDay(String date);

    /**
     * 一周汇交量
     * @return
     */
    Integer findYzhjCount();

    /**
     * 总业务量
     * @return
     */
    Integer countDataTotal();

    /**
     * 抵押业务量
     * @return
     */
    Integer countDataByDyaq();

    /**
     * 查封业务量
     * @return
     */
    Integer countDataByCfdj();

    /**
     * 注销业务量
     * @return
     */
    Integer countDataByZxdj();

    /**
     * 各区县上报情况
     * @return
     */
    List<BdtjReq> countDataByArea();

    /**
     * 各区县近一周上报情况
     * @return
     */
    List<BdtjReq> countDataByWeek();

    /**
     * 各区一个月上报情况
     * @return
     */
    List<BdtjReq> countDataByMouth();

    /**
     *
     * 各区一周上报情况
     *
     * @return
     */
    List<BdtjReq> countDataByWeeks();

    /**
     * 各区县一日上报情况
     * @return
     */
    List<BdtjReq> countDataByDays();


    List<BdtjReq> getPieCounts(@Param("start") String start, @Param("end") String end);




}
