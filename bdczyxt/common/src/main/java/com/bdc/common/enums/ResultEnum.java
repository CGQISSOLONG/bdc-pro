package com.bdc.common.enums;

public enum ResultEnum {

    /**
     * 1开头表示请求接口参数校验状态码
     */
    PARAMETER_ERROR(10001, "参数错误"),

    /**
     * 2开头表示用户相关状态码
     */
    LOGIN_ERROR(20001,"登陆失败,请重试！！！"),
    ACCOUNT_DISABLE_ERROR(20002,"账号被禁用，无法登陆"),
    USERNAME_IS_NULL(20003,"用户名不能为空"),
    PASSWORD_IS_NULL(20004,"密码不能为空"),
    REQUEST_TIMEOUT_ERROR(20005,"登陆超时，请重新登陆！！！"),
    LOGIN_ACCOUNT_IS_NULL(20006,"登录失败,用户不存在"),
    LOGIN_PASSWORD_ERROR(20007,"登录失败,密码错误"),
    ACCOUNT_IS_REGISTERED_ERROR(20008,"手机号已经被注册"),
    UNAUTHENTICATION(20009,"获取登录用户信息失败"),
    VERIFICATION_CODE_INVALID(20010,"验证码无效"),
    NEW_PASSWORD_IS_NULL(20011,"新密码为空！！！"),
    CONFIRM_PASSWORD_IS_NULL(20012,"确认密码为空！！！"),
    PASSWORD_IS_INCONSISTENT(20013,"两次密码不一致！！！"),
    ACCOUNT_IS_NULL(20014,"账号不存在"),
    ORIGIN_ACCOUNT_IS_NULL(20015,"原账号不存在"),
    ORIGIN_NEW_ACCOUNT_IS_SAME(20016,"与原账号一致，无需修改"),
    PASSWORD_IS_ERROR(20017,"密码错误"),
    ACCOUNT_PASSWORD_IS_ERROR(20018,"用户名或者密码错误，请重试！！！"),
    VERIFICATION_CODE_ERROR(20019,"验证码错误"),
    NO_JURISDICTION(20020, "权限不足"),


    /**
     * 3开头表示crud状态码
     */
    INSERT_ERROR(30001, "保存失败,请重试！！！"),
    DELETE_ERROR(30002, "删除失败,请重试！！！"),
    SELECT_ERROR(30003, "加载失败,请重试！！！"),
    UPDATE_ERROR(30004, "更新失败,请重试！！！"),
    UPLOAD_ERROR(30005, "上传失败,请重试！！！"),
    UPLOAD_TYPE_ERROR(30006, "上传文件类型必须为zip！"),
    SELECT_DATA_IS_NULL(30007,"查询数据不存在！！！"),

    /**
     * 成功系统异常状态码
     */
    UNKNOWN_ERROR(-1, "系统内部异常,请联系管理员!!!"),
    SUCCESS(200, "success");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
