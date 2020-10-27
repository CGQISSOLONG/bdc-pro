package com.bdc.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
public class City {

    private Long id;
    /**
     * 城市名称
     */
    private String name;
    /**
     * 1为默认
     */
    private int isdefault;

    /**
     * 城市所有区县代码前缀
     */
    private String cityCodePrefix;

    /**
     * 创建时间
     */
    private Date createDate;
}
