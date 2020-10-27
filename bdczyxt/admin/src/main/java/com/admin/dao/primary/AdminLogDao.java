package com.admin.dao.primary;

import com.bdc.common.base.CrudDao;
import com.admin.controller.admin.req.AdminLogReq;
import com.admin.entity.primary.AdminLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 创建人：laill
 * @version 版本号：V1.0
 * <p>
 * **************************修订记录***************************************
 * <p>
 * 2019年06月12日 laill 创建该类功能。
 * <p>
 * ***********************************************************************
 * </p>
 * @ClassName 类名：AdminLogDao
 * @Description 功能说明：后台日志dao
 * <p>
 * TODO
 * </p>
 * ***********************************************************************
 * @date 创建日期：2019年06月12日
 */
@Repository
@Mapper
public interface AdminLogDao extends CrudDao<AdminLog> {

    /**
     * 查询日志
     * @param adminLogReq
     * @return
     */
    List<AdminLog> findLogList(AdminLogReq adminLogReq);

    void deleteAll();

//    void delete(Integer id);

    void save(AdminLog adminLog);
}
