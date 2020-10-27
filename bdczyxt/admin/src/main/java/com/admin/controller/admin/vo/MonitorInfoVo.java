package com.admin.controller.admin.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author 创建人：laill
 * @version 版本号：V1.0
 * <p>
 * **************************修订记录***************************************
 * <p>
 * 2019年06月13日 laill 创建该类功能。
 * <p>
 * ***********************************************************************
 * </p>
 * @ClassName 类名：MonitorInfoVo
 * @Description 功能说明：系统首页vo实体
 * <p>
 * TODO
 * </p>
 * ***********************************************************************
 * @date 创建日期：2019年06月13日
 */
@Data
public class MonitorInfoVo {
    /**
     * 注册用户量
     */
    private Integer registerUser;

    /**
     * 系统信息
     */
    private Map<String, String>  systemInfo;
}
