package com.bdc.util;

/**
 * @ClassName 类名：UrlConstant
 * @Description 功能说明：url常量
 */
public interface UrlConstant {
    /**
     * 新增
     */
    String CREATE = "/create";

    /**
     * 修改
     */
    String UPDATE = "/update";

    /**
     * 删除（单条）
     */
    String DELETE = "/delete";

    /**
     * 删除（批量）
     */
    String BATCH_DELETE = "/batchdelete";

    /**
     * 首页
     */
    String INDEX = "/index";

    /**
     * 列表
     */
    String LIST = "/list";

    /**
     * 全部
     */
    String ALL = "/all";

    /**
     * 单条
     */
    String ONE = "/one";

    /**
     * 详情
     */
    String DETAIL = "/detail";

    /**
     * 分页
     */
    String QUERY_PAGE = "/pages";
    /**
     * 状态修改
     */
    String TOGGLESTATUS = "/togglestatus";

    /**
     * 树列表
     */
    String TREELIST = "/treeList";

    /**
     * 登录
     */
    String LOGIN = "/login";

    /**
     * 数量
     */
    String COUNT = "/count";

    /**
     * 上传
     */
    String UPLOAD = "/upload";

    /**
     * 检测
     */
    String CHECK = "/check";

    /**
     * 表单
     */
    String FORM = "/form";

    /**
     * 退出登陆
     */
    String LOGOUT="logout";

    /**
     * 修改账号
     */
    String MODIFY_ACCOUNT = "modifyAccount";

    /**
     * 修改密码
     */
    String MODIFY_PASSWORD = "modifyPassword";

    /**
     * 找回密码
     */
    String RETRIEVE_PASSWORD="retrievePassword";
}
