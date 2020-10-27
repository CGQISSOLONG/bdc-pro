package com.admin.controller.admin.req;

import lombok.Data;

/**
 * @author 创建人：laill
 * @version 版本号：V1.0
 * <p>
 * **************************修订记录***************************************
 * <p>
 * 2019年06月05日 laill 创建该类功能。
 * <p>
 * ***********************************************************************
 * </p>
 * @ClassName 类名：AdminUserReq
 * @Description 功能说明：用户请求req
 * <p>
 * TODO
 * </p>
 * ***********************************************************************
 * @date 创建日期：2019年06月05日
 */
@Data
public class AdminUserReq {

    /** 登录名称 */
    private String account;

    /** 密码 */
    private String password;

    /** 邮箱 */
    private String email;
}
