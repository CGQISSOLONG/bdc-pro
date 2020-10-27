package com.bdc.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "area")
@Data
public class Area {
    /**
     * 标识码
     */
    private Long id;
    /**
     * 地区名称
     */
    private String name;
    /**
     * 区县代码
     */
    private String qxdm;
    /**
     * 城市编码
     */
    private String citycode;
    /**
     * 是否默认系统地区（0：不是，1：是）
     */
	private Integer isdefault;
    /**
     * 城市标识码
     */
    private Long cityId;
}
