package com.bdc.dao;

import com.bdc.dao.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 创建人：laill
 * @version 版本号：V1.0
 * <p>
 * **************************修订记录***************************************
 * <p>
 * 2019年11月18日 laill 创建该类功能。
 * <p>
 * ***********************************************************************
 * </p>
 * @ClassName 类名：HjfxDao
 * @Description 功能说明：汇交分析
 * <p>
 * TODO
 * </p>
 * ***********************************************************************
 * @date 创建日期：2019年11月18日
 */
@Mapper
public interface HjfxDao {
    List<Map<String, Integer>> getHjYzRateData(String sj);

    List<HjyzrateDto> getFormHjYzRateData(String sj);

    List<OnlineratioDto> getFormOnlineData(String sj);

    List<AverageTimeDto> getFormYsData(String sj);

    List<Map<String, Integer>> getWsqlData(String sj);

    List<Map<String, Integer>> getYsData(String sj);

    List<YzlJcSystemDto> getYzlJcData(@Param("sj") String sj, @Param("qxdm") String qxdm);

    List<HjfxMx> getFormYsTotalDataMx(@Param("sj") String sj, @Param("qxdm") Integer qxdm);

    List<HjfxMx> getFormOnlineWebTotalDataMx(@Param("sj") String sj, @Param("qxdm") Integer qxdm);

    List<HjfxMx> getFormOnlineTotalDataMx(@Param("sj") String sj, @Param("qxdm") Integer qxdm);

    List<BwmxDto> getBwjrMx(@Param("sj") String sj, @Param("qxdm") Integer qxdm);

    List<SjhjMxOtherDto> getSjhjMxOther(@Param("sj") String sj, @Param("qxdm") Integer qxdm);

}
