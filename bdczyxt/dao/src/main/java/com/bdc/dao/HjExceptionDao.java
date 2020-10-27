package com.bdc.dao;


import com.bdc.entity.HjExceptionLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HjExceptionDao {
    List<HjExceptionLog> findAllLog();
}
