package com.bdc.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "djlx")
@Data
public class Djlx {
    /**
     * 标识码
     */
    @Id
    private String id;
    /**
     * code
     */
    private Integer code;
    /**
     * 名称
     */
    private String name;

}
