/**
 * Copyright &copy; 2012-2016 <a href="http://www.*.com">cmck</a> All rights reserved.
 */
package com.bdc.dao;


import com.bdc.common.base.CrudDao;
import com.bdc.entity.primary.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典DAO接口
 */
@Mapper
public interface DictDao extends CrudDao<Dict> {

    void updateStatusById(@Param("id") Integer dictId, @Param("status") Integer status);

    void updateById(@Param("id") Integer dictId, @Param("dict") Dict dict);

    List<Dict> findQxdmListByCityCodePrefix(String cityCodePrefix);

    List<Dict> findMenuTypeList();

    List<Dict> findTjfl();
}
