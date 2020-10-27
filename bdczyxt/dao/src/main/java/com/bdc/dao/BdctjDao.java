
package com.bdc.dao;


import com.bdc.dao.req.BdtjReq;
import com.bdc.dao.req.BjfxReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 不动产统计-基础统计模块
 *
 * @author luoxuan
 * @version 2019-10-16 20:09:55
 */
@Mapper
public interface BdctjDao {


    /***
     * @Description: 汇聚总量-按业务量计算
     */
    Integer countData();

    /***
     * @Description: 查询某一天的汇聚量
     */
    Integer countDataByDay(@Param("date") String date);

    /**
     * 汇交总量
     *
     * @return
     */
    Integer findHjzlCount();

    /**
     * 昨日汇交量
     *
     * @return
     */
    Integer findZrhjCount();

    /**
     * 一周汇交量
     */
    Integer findYzhjCount();

    /**
     * 柱状图数据
     *
     * @param bjfxReq
     * @return
     */
    List<Map<String, Integer>> getHistogramData(BjfxReq bjfxReq);

    /**
     * 折线图数据
     *
     * @param bjfxReq
     * @return
     */
    List<Map<String, Integer>> geBrokenLinetData(BjfxReq bjfxReq);

    /**
     * 饼图数据
     *
     * @param bjfxReq
     * @return
     */
    List<Map<String, Integer>> getCakeshapeData(BjfxReq bjfxReq);

    /**
     * 地图 办件量
     *
     * @param bjfxReq
     * @return
     */
    List<Map<String, String>> getMapBjlData(BjfxReq bjfxReq);

    /**
     * 地图 证书量
     *
     * @param bjfxReq
     * @return
     */
    List<Map<String, String>> getMapZslData(BjfxReq bjfxReq);

    /**
     * 地图 证明量
     *
     * @param bjfxReq
     * @return
     */
    List<Map<String, String>> getMapZmlData(BjfxReq bjfxReq);

    /***
     * @Description: 总业务量
     * @author shaobing
     * @Date 2019/10/23
     * @Version 1.0
     * @Param []
     * @return java.lang.Integer
     */
    Integer countDataTotal();

    /***
     * @Description: 抵押业务量
     * @author shaobing
     * @Date 2019/10/23
     * @Version 1.0
     * @Param []
     * @return java.lang.Integer
     */
    Integer countDataByDyaq();

    /***
     * @Description: 查封业务量
     * @author shaobing
     * @Date 2019/10/23
     * @Version 1.0
     * @Param []
     * @return java.lang.Integer
     */
    Integer countDataByCfdj();

    /***
     * @Description: 注销业务量
     * @author shaobing
     * @Date 2019/10/23
     * @Version 1.0
     * @Param []t
     * @return java.lang.Integer
     */
    Integer countDataByZxdj();

    /***
     * @Description: 各区县上报情况
     * @author shaobing
     * @Date 2019/10/23
     * @Version 1.0
     * @Param []
     * @return com.zhenshan.req.HjtjReq
     */
    List<BdtjReq> countDataByArea(@Param("start") String start, @Param("end") String end);

    /***
     * @Description: 各区县近一周上报情况
     * @author shaobing
     * @Date 2019/10/30
     * @Version 1.0
     * @Param []
     * @return com.zhenshan.req.HjtjReq
     */
    List<BdtjReq> countDataByWeek();


    /**
     * 各区县一个月上报情况
     * @return
     */
    List<BdtjReq> countDataByMouth();

    /**
     * 各区县一周上报情况
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
