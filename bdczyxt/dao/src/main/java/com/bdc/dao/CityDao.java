package com.bdc.dao;

import com.bdc.entity.City;

public interface CityDao {
    City findCityByCityCodePrefix(String cityCodePrefix);
}
