package com.bdc.controller.api;

import com.alibaba.druid.util.StringUtils;
import com.bdc.common.BdcProtection;
import com.bdc.common.base.BaseResult;
import com.bdc.dao.AreaDao;
import com.bdc.dao.BdctjDao;
import com.bdc.dao.CityDao;
import com.bdc.dao.req.BjfxReq;
import com.bdc.entity.Area;
import com.bdc.entity.City;
import com.bdc.util.ResultUtil;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "不动产统计-办件分析")
@RestController
@BdcProtection
@RequestMapping("/bjfx")
public class BjfxController extends BaseController{
    @Autowired
    private BdctjDao bdctjDao;

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private CityDao cityDao;

    @Value("1997")
    private  String cityCodePrefix;

    @ModelAttribute
    public BjfxReq getBjfxReq(BjfxReq bjfxReq) {
        if (bjfxReq == null) {
            bjfxReq = new BjfxReq();
        }
        bjfxReq.setQxdmTemp(cityCodePrefix);
        return bjfxReq;
    }

    /**
     * 柱状图数据
     *
     * @param bjfxReq
     * @return
     */
    @ApiOperation(value = "办件分析-柱状图", notes = "办件分析")
    @PostMapping("/getHistogramData")
    public BaseResult getHistogramData(BjfxReq bjfxReq) {
        List<Map<String, Integer>> map = bdctjDao.getHistogramData(bjfxReq);
        return ResultUtil.success(map);
    }

    /**
     * 折线图数据
     *
     * @param bjfxReq
     * @return
     */
    @ApiOperation(value = "办件分析-折线图", notes = "办件分析")
    @PostMapping("/geBrokenLineData")
    public BaseResult geBrokenLinetData(BjfxReq bjfxReq) {
        List<Map<String, Integer>> map = bdctjDao.geBrokenLinetData(bjfxReq);
        return ResultUtil.success(map);
    }

    /**
     * 饼图数据
     *
     * @param bjfxReq
     * @return
     */
    @ApiOperation(value = "办件分析-饼图", notes = "办件分析")
    @PostMapping("/getCakeshapeData")
    public BaseResult getCakeshapeData(BjfxReq bjfxReq) {
        List<Map<String, Integer>> map = bdctjDao.getCakeshapeData(bjfxReq);
        return ResultUtil.success(map);
    }

    /**
     * 地图
     *
     * @param bjfxReq
     * @return
     */
    @ApiOperation(value = "办件分析-地图", notes = "办件分析")
    @PostMapping("/getMapData")
    public BaseResult getMapData(BjfxReq bjfxReq) {
        List<Map<String, String>> map = Lists.newArrayList();
        if ("1".equals(bjfxReq.getZl())) {
            map = bdctjDao.getMapBjlData(bjfxReq);
        } else if ("2".equals(bjfxReq.getZl())) {
            map = bdctjDao.getMapZslData(bjfxReq);
        } else if ("3".equals(bjfxReq.getZl())) {
            map = bdctjDao.getMapZmlData(bjfxReq);
        }
        City city=cityDao.findCityByCityCodePrefix(cityCodePrefix);
        List<Area> list = areaDao.findAreaByCityId(Long.valueOf(city.getId()));
        for (Area area : list) {
            boolean t = true;
            for (Map<String, String> qxMap : map) {
                if (StringUtils.equals(area.getQxdm(), String.valueOf(qxMap.get("qxdm")))) {
                    t = false;
                    continue;
                }
            }
            if (t) {
                final HashMap<String, String> stringStringHashMap = new HashMap<>(3);
                stringStringHashMap.put("value", "0");
                stringStringHashMap.put("qxdm", area.getQxdm());
                stringStringHashMap.put("name", area.getName());
                map.add(stringStringHashMap);
            }
        }


        return ResultUtil.success(map);
    }


}
