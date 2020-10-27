package com.admin.dao.primary;

import com.admin.controller.bdc.req.PubUserLoginLogReq;
import com.bdc.common.base.CrudDao;
import com.bdc.entity.primary.UserLoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName 类名：AdminLogDao
 * @Description 功能说明：后台日志dao
 */
@Mapper
@Repository
public interface PubUserLoginLogDao extends CrudDao<UserLoginLog> {

    /**
     * 查询日志
     * @return
     */
    List<UserLoginLog> findLogList(PubUserLoginLogReq logReq);

    void deleteAll();
}
