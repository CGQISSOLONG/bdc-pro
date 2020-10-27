/**
 * Copyright &copy; 2012-2016 <a href="http://www.*.com">cmck</a> All rights reserved.
 */
package com.bdc.dao;

import com.bdc.common.base.CrudDao;
import com.bdc.entity.Djlx;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典DAO接口
 * @author attendance
 * @version 2014-05-16
 */
@Mapper
public interface DjlxDao extends CrudDao<Djlx> {

    List<Djlx> findAll();
}
