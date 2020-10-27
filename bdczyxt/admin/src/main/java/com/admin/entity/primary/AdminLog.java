package com.admin.entity.primary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by laill
 * 操作日志
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminLog implements Serializable {
    private static final long serialVersionUID = 3659157778778329140L;

    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 操作url
     */
    private String url;

    /**
     * 用户操作
     */
    private String operation;

    /**
     * 操作者
     */
    private String userName;

    /**
     * ip
     */
    private String ip;

    /**
     * 创建时间
     */
    private String createTime;

}
