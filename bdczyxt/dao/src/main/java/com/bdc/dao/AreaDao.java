package com.bdc.dao;

import com.bdc.entity.Area;

import java.util.List;

public interface AreaDao {
    List<Area> findAreaByCityId(Long cityId);
}
