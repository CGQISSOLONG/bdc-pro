package com.bdc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author luoxuan
 * @time 2018/7/10 19:10.
 * Description:龙腾汇聚平台接口日志 记录每次请求
 */

@Data
@NoArgsConstructor
public class ApiLtLog {

    private Integer id;
    private String ip;
   // @Column(length = 4000)
    private String resultData;
    private Date cxsj = new Date();
    private String params;
    private String methods;
    private String apiName;
    /**
     * 请求状态  0.系统异常 1.正常返回，2.验证失败 3.参数异常
     */
    private Integer status;
   // @Column(length = 4000)
    private String errorMsg;

    private Date cxkssj;
    private Date cxjssj;

    @Transient
    private String statusStr;

    public ApiLtLog(String ip, String params, String methods, String apiName) {
        this.ip = ip;
        this.params = params;
        this.methods = methods;
        this.apiName = apiName;
        this.cxkssj = new Date();
    }

    public ApiLtLog(String ip, String params, String methods, String apiName, Date cxkssj) {
        this.ip = ip;
        this.params = params;
        this.methods = methods;
        this.apiName = apiName;
        this.cxkssj = cxkssj;
    }
}
