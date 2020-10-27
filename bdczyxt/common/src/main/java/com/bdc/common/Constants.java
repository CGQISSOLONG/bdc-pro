package com.bdc.common;
import java.util.HashSet;
import java.util.Set;

public final class Constants {
    private Constants() {}

    /**
     * 是否/开启关闭/删除
     */
    public static final String YES = "1", NO = "0";


    /**
     * 是否免登陆
     */
    public static Boolean IS_PASS = false;

    public static Set<String> METHOD_URL_SET = new HashSet<>();


    /******************************用户类型*********************************/
    /**
     * 公众用户
     */
    public static final Integer USER_TYPE_PUBLIC=0;

    /**
     * 管理员
     */
    public static final Integer USER_TYPE_ADMIN=1;

    /**
     * 超级管理员
     */
    public static final Integer USER_TYPE_SUPER_ADMIN=2;

    /**
     * 政府部门
     */
    public static final Integer USER_TYPE_JIANFANG_THREE=3;

    /************************用户状态1. 在用。2.禁用。0.删除****************************/
    public static final Integer USER_STATUS_ENABLE=1;

    public static final Integer USER_STATUS_DISABLE=2;

    public static final Integer USER_STATUS_DELETE=0;

    /************************角色状态 1. 在用。2.禁用。0.删除****************************/
    public static final Integer ROLE_STATUS_ENABLE=1;

    public static final Integer ROLE_STATUS_DISABLE=2;

    public static final Integer ROLE_STATUS_DELETE=0;


    public static final String MD5_KEY = "lkfg";
}
