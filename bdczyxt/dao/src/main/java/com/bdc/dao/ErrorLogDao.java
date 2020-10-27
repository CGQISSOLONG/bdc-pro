package com.bdc.dao;

import com.bdc.common.base.CrudDao;
import com.bdc.dao.req.ErrorLogReq;
import com.bdc.entity.primary.ErrorLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName 类名：AdminLogDao
 * @Description 功能说明：后台日志dao
 */
@Mapper
@Repository
public interface ErrorLogDao extends CrudDao<ErrorLog> {

    /**
     * 查询日志
     * @param errorLogReq
     * @return
     */
    List<ErrorLog> findLogList(ErrorLogReq errorLogReq);

    void deleteAll();

    ErrorLog findOne(Integer id);

    void save(ErrorLog errorLog);

    void deletes(Integer id);

}
