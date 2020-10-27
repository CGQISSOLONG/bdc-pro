/**
 * Copyright &copy; 2012-2016 <a href="http://www.*.com">cmck</a> All rights reserved.
 */
package com.bdc.dao;


import com.bdc.dao.dto.HjjrqdDto;
import com.bdc.dao.req.HjjrqdReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 不动产统计-基础统计模块
 *
 * @author luoxuan
 * @version 2019-10-16 20:09:55
 */
@Mapper
public interface HjjrqdDao {


    List<HjjrqdDto> getHjjrqdData(HjjrqdReq hjjrqdReq);
}
